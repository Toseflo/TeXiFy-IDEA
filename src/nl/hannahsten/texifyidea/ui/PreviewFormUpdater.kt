package nl.hannahsten.texifyidea.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.util.io.FileUtil.createTempDirectory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.hannahsten.texifyidea.settings.sdk.LatexSdkUtil
import nl.hannahsten.texifyidea.util.SystemEnvironment
import nl.hannahsten.texifyidea.util.runCommandWithExitCode
import org.apache.batik.dom.GenericDOMImplementation
import org.apache.batik.svggen.SVGGeneratorContext
import org.apache.batik.svggen.SVGGraphics2D
import org.apache.batik.transcoder.TranscoderException
import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.PNGTranscoder
import org.scilab.forge.jlatexmath.DefaultTeXFont
import org.scilab.forge.jlatexmath.ParseException
import org.scilab.forge.jlatexmath.TeXConstants
import org.scilab.forge.jlatexmath.TeXFormula
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration
import org.scilab.forge.jlatexmath.greek.GreekRegistration
import java.awt.Color
import java.awt.Dimension
import java.awt.Insets
import java.io.*
import java.nio.file.Paths
import javax.imageio.ImageIO
import javax.swing.JLabel
import javax.swing.SwingUtilities.invokeLater

/**
 * @author Sergei Izmailov
 */
class PreviewFormUpdater(private val previewForm: PreviewForm) {

    /**
     * The default preamble.
     *
     * Unless you are going to set your own \pagestyle{}, simply append to this variable.
     */
    private val defaultPreamble =
        """
        \pagestyle{empty}
        """.trimIndent()

    /**
     * Modify this variable to include more packages.
     */
    var preamble = defaultPreamble

    /**
     * Controls how long (in seconds) we will wait for the document compilation. If the time taken exceeds this,
     * we will return an error and not output a preview.
     */
    var waitTime = 3L

    /**
     * Reset the preamble to the default preamble.
     */
    fun resetPreamble() {
        preamble = defaultPreamble
    }

    /**
     * First define the function that actually does stuff in a temp folder. The usual temp directory might not be
     * accessible by inkscape (e.g., when inkscape is a snap), and using function we can specify an alternative
     * temp directory in case the usual fails.
     */
    private fun setPreviewCodeInTemp(tempDirectory: File, previewCode: String, project: Project) {
        try {
            val tempBasename = Paths.get(tempDirectory.path.toString(), "temp").toString()
            val writer = PrintWriter("$tempBasename.tex", "UTF-8")

            val tmpContent =
                """\documentclass{article}
$preamble
\begin{document}
$previewCode
\end{document}"""
            writer.println(tmpContent)
            writer.close()

            val latexStdoutText = runPreviewFormCommand(
                LatexSdkUtil.getExecutableName("pdflatex", project),
                arrayOf(
                    "-interaction=nonstopmode",
                    "-halt-on-error",
                    "$tempBasename.tex"
                ),
                tempDirectory
            ) ?: return

            runInkscape(tempBasename, tempDirectory)
            val image = ImageIO.read(File("$tempBasename.png"))
            invokeLater {
                previewForm.setPreview(image, latexStdoutText)
            }
        }
        finally {
            // Delete all the created temp files in the default temp directory.
            tempDirectory.deleteRecursively()
        }
    }

    /**
     * Compile the preview with jlatexmath and save as tempBaseName.png.
     */
    private fun jlatexmathPreview(previewCode: String) {
        val tempBaseName = "temp"
        try {
            // Fonts should be shapes, otherwise the user would have needed to install the font to render it
            // when converting to png (because fonts are not saved in svg).
            toSVG(previewCode, tempBaseName, fontAsShapes = true)
            saveSvgAsPng(tempBaseName)
            val image = ImageIO.read(File("$tempBaseName.png"))
            invokeLater {
                previewForm.setPreview(image, "")
            }
        }
        catch (e: ParseException) {
            previewForm.setLatexErrorMessage(e.message ?: "There was an unknown problem with compiling the preview.")
        }
    }

    /**
     * Sets the code that will be previewed, whether that be an equation, a tikz picture, or whatever else
     * you are trying to preview.
     *
     * This function also starts the creation and compilation of the temporary preview document, and will then
     * either display the preview, or if something failed, the error produced.
     */
    fun compilePreview(previewCode: String, project: Project) {
        previewForm.setEquation(previewCode)

        // jlatexmath cannot handle a custom preamble
        // todo check for custom preamble
        if (true) {
            jlatexmathPreview(previewCode, )
        }
        else {

            GlobalScope.launch {
                try {
                    // Snap apps are confined to the users home directory
                    if (SystemEnvironment.isInkscapeInstalledAsSnap) {
                        @Suppress("BlockingMethodInNonBlockingContext")
                        setPreviewCodeInTemp(
                            createTempDirectory(File(System.getProperty("user.home")), "preview", null),
                            previewCode,
                            project
                        )
                    }
                    else {
                        @Suppress("BlockingMethodInNonBlockingContext")
                        setPreviewCodeInTemp(createTempDirectory("preview", null), previewCode, project)
                    }
                }
                catch (exception: AccessDeniedException) {
                    previewForm.setLatexErrorMessage("${exception.message}")
                }
                catch (exception: IOException) {
                    previewForm.setLatexErrorMessage("${exception.message}")
                }
            }
        }
    }

    @Throws(FileNotFoundException::class, TranscoderException::class, IOException::class)
    private fun saveSvgAsPng(tempBaseName: String) {
        val ti = TranscoderInput(FileInputStream("$tempBaseName.svg"))
        val os = FileOutputStream("$tempBaseName.png")
        val to = TranscoderOutput(os)
        val pt = PNGTranscoder()
        pt.transcode(ti, to)
        os.flush()
        os.close()
    }

    /**
     * https://github.com/opencollab/jlatexmath/blob/af77a8e80d41ff67dfe2f42f14b41f6860dfeeec/jlatexmath-example-export/src/test/java/org/scilab/forge/jlatexmath/examples/export/Convert.java#L39
     */
    @Throws(IOException::class)
    fun toSVG(latex: String?, tempBaseName: String, fontAsShapes: Boolean) {
        val domImpl = GenericDOMImplementation.getDOMImplementation()
        val svgNS = "http://www.w3.org/2000/svg"
        val document = domImpl.createDocument(svgNS, "svg", null)
        val ctx = SVGGeneratorContext.createDefault(document)
        val g2 = SVGGraphics2D(ctx, fontAsShapes)
        DefaultTeXFont.registerAlphabet(CyrillicRegistration())
        DefaultTeXFont.registerAlphabet(GreekRegistration())
        val formula = TeXFormula(latex)
        val icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20f)
        icon.insets = Insets(5, 5, 5, 5)
        g2.svgCanvasSize = Dimension(icon.iconWidth, icon.iconHeight)
        g2.color = Color.white
        g2.fillRect(0, 0, icon.iconWidth, icon.iconHeight)
        val jl = JLabel()
        jl.foreground = Color(0, 0, 0)
        icon.paintIcon(jl, g2, 0, 0)
        val useCSS = true
        val svgs = FileOutputStream("$tempBaseName.svg")
        val out: Writer = OutputStreamWriter(svgs, "UTF-8")
        g2.stream(out, useCSS)
        svgs.flush()
        svgs.close()
    }

    /**
     * Run inkscape command to convert pdf to png, depending on the version of inkscape.
     */
    private fun runInkscape(tempBasename: String, tempDirectory: File) {
        // If 1.0 or higher
        if (SystemEnvironment.inkscapeMajorVersion >= 1) {
            runPreviewFormCommand(
                inkscapeExecutable(),
                arrayOf(
                    "$tempBasename.pdf",
                    "--export-area-drawing",
                    "--export-dpi", "1000",
                    "--export-background", "#FFFFFF",
                    "--export-background-opacity", "1.0",
                    "--export-filename", "$tempBasename.png"
                ),
                tempDirectory
            ) ?: throw AccessDeniedException(tempDirectory)
        }
        else {
            runPreviewFormCommand(
                pdf2svgExecutable(),
                arrayOf(
                    "$tempBasename.pdf",
                    "$tempBasename.svg"
                ),
                tempDirectory
            ) ?: return

            runPreviewFormCommand(
                inkscapeExecutable(),
                arrayOf(
                    "$tempBasename.svg",
                    "--export-area-drawing",
                    "--export-dpi", "1000",
                    "--export-background", "#FFFFFF",
                    "--export-png", "$tempBasename.png"
                ),
                tempDirectory
            ) ?: throw AccessDeniedException(tempDirectory)
        }
    }

    private fun runPreviewFormCommand(command: String, args: Array<String>, workDirectory: File): String? {

        val result = runCommandWithExitCode(command, *args, workingDirectory = workDirectory, timeout = waitTime)

        if (result.second != 0) {
            previewForm.setLatexErrorMessage("$command exited with ${result.second}\n${result.first ?: ""}")
            return null
        }

        return result.first
    }

    private fun inkscapeExecutable(): String {
        var suffix = ""
        if (SystemInfo.isWindows) {
            suffix = ".exe"
        }
        return "inkscape$suffix"
    }

    private fun pdf2svgExecutable(): String {
        var suffix = ""
        if (SystemInfo.isWindows) {
            suffix = ".exe"
        }
        return "pdf2svg$suffix"
    }
}
