package nl.hannahsten.texifyidea.action.wizard.graphic

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import nl.hannahsten.texifyidea.action.EditorAction
import nl.hannahsten.texifyidea.action.insert.InsertTable
import nl.hannahsten.texifyidea.action.wizard.table.TableInformation
import nl.hannahsten.texifyidea.lang.LatexPackage
import nl.hannahsten.texifyidea.lang.graphic.CaptionLocation
import nl.hannahsten.texifyidea.ui.tablecreationdialog.ColumnType
import nl.hannahsten.texifyidea.ui.tablecreationdialog.TableCreationDialogWrapper
import nl.hannahsten.texifyidea.util.*
import nl.hannahsten.texifyidea.util.files.psiFile
import nl.hannahsten.texifyidea.util.files.removeFileExtension
import java.io.File
import java.util.*

/**
 * Action that shows a dialog with a graphic insertion wizard, and inserts the graphic as latex at the location of the
 * cursor.
 *
 * @author Hannah Schellekens
 */
class InsertGraphicWizardAction(val initialFile: File? = null) : AnAction() {

    /**
     * Opens and handles the graphic insertion wizard.
     */
    fun executeAction(file: VirtualFile, project: Project) {
        val editor = project.currentTextEditor() ?: return
        val document = editor.editor.document

        // Get the indentation from the current line.
        val indent = document.lineIndentationByOffset(editor.editor.caretOffset())

        // Create the dialog.
        val dialogWrapper = InsertGraphicWizardDialogWrapper(initialFilePath = initialFile?.absolutePath ?: "")

        // If the user pressed OK, do stuff.
        if (!dialogWrapper.showAndGet()) return

        // Handle result.
        val graphicData = dialogWrapper.extractData()
        file.psiFile(project)?.let { graphicData.importPackages(it) }
        editor.editor.insertGraphic(graphicData, indent)
    }

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(PlatformDataKeys.VIRTUAL_FILE) ?: return
        val project = e.getData(PlatformDataKeys.PROJECT) ?: return
        executeAction(file, project)
    }

    private fun Editor.insertGraphic(data: InsertGraphicData, indent: String, tab: String = "    ") {
        // Only the graphics (non-centered).
        val toInsert = if (data.center.not() && data.placeInFigure.not()) {
            data.includeCommand()
        }
        // Centered graphics, but not in a figure.
        else if (data.center && data.placeInFigure.not()) {
            buildString {
                append("\\begin{center}\n")
                append(indent).append(tab).append(data.includeCommand()).newline()
                append(indent).append("\\end{center}")
            }
        }
        // Insert figure.
        else data.figure(indent, tab)

        insertAtCaretAndMove(toInsert)
    }

    private fun InsertGraphicData.figure(indent: String, tab: String) = buildString {
        append("\\begin{figure}")
        if (positions?.isNotEmpty() == true) {
            append(positionOptions())
        }
        newline()

        if (center) {
            append(indent).append(tab).append("\\centering").newline()
        }

        if (captionLocation == CaptionLocation.ABOVE_GRAPHIC) {
            addCaptionAndLabel(this@figure, indent, tab)
        }

        append(indent).append(tab).append(includeCommand()).newline()

        if (captionLocation == CaptionLocation.BELOW_GRAPHIC) {
            addCaptionAndLabel(this@figure, indent, tab)
        }

        append(indent).append("\\end{figure}")
    }

    private fun StringBuilder.addCaptionAndLabel(data: InsertGraphicData, indent: String, tab: String) {
        append(indent).append(tab).append(data.captionCommand()).newline()
        append(indent).append(tab).append("\\label{").append(data.label ?: "").append("}").newline()
    }

    private fun InsertGraphicData.includeCommand() = buildString {
        append("\\includegraphics")
        if (options.isNotBlank()) {
            append("[").append(options).append("]")
        }
        append("{").append(convertFilePath(filePath)).append("}")
    }

    private fun convertFilePath(filePath: String): String {
        // TODO: Automagically convert to relative path.
        return filePath.removeFileExtension()
    }

    private fun InsertGraphicData.captionCommand() = buildString {
        append("\\caption")
        if (shortCaption?.isNotBlank() == true) {
            append("[").append(shortCaption).append("]")
        }
        append("{").append(caption ?: "").append("}")
    }

    private fun InsertGraphicData.importPackages(file: PsiFile) {
        WriteCommandAction.runWriteCommandAction(file.project) {
            file.insertUsepackage(LatexPackage.GRAPHICX)
            positions?.forEach { location ->
                location.requiredPackage?.let {
                    file.insertUsepackage(it)
                }
            }
        }
    }

    private fun InsertGraphicData.positionOptions() = buildString {
        append("[")
        append(positions?.joinToString("") { it.symbol })
        append("]")
    }
}
