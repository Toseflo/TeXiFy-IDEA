package nl.hannahsten.texifyidea.run

import com.intellij.execution.ExecutionException
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.util.io.isDirectory
import nl.hannahsten.texifyidea.run.compiler.latex.LatexCompiler
import nl.hannahsten.texifyidea.util.files.FileUtil
import nl.hannahsten.texifyidea.util.files.createExcludedDir
import nl.hannahsten.texifyidea.util.files.psiFile
import nl.hannahsten.texifyidea.util.files.referencedFileSet
import java.io.File
import java.nio.file.Path

/**
 * Output or auxiliary path of the run configuration.
 */
class LatexRunConfigurationOutputPathOption : LatexRunConfigurationDirectoryOption() {

    /**
     * Get path to output file (e.g. pdf)
     */
    fun getOutputFilePath(options: LatexRunConfigurationOptions, project: Project): String {
        val outputDir = getOrCreateOutputPath(options.mainFile.resolve(), project)
        return "${outputDir?.path}/" + options.mainFile.resolve()!!
            .nameWithoutExtension + "." + if (options.outputFormat == LatexCompiler.OutputFormat.DEFAULT) "pdf"
        else options.outputFormat.toString()
            .toLowerCase()
    }

    /**
     * Tries to resolve the [resolvedPath], and if it doesn't exist, tries to create it (if it's a directory).
     */
    fun getOrCreateOutputPath(mainFile: VirtualFile?, project: Project): VirtualFile? {
        val outPath = resolvedPath ?: return null
        val resolved = resolve()
        if (resolved != null) return resolved

        // Can be improved by assuming a relative path to the project, using given context
        if (outPath.isBlank() || !Path.of(outPath).isAbsolute || !Path.of(outPath).isDirectory() || mainFile == null) return null
        val fileIndex = ProjectRootManager.getInstance(project).fileIndex

        // Create output path for non-MiKTeX systems (MiKTeX creates it automatically)
        val module = fileIndex.getModuleForFile(mainFile, false)
        if (File(outPath).mkdirs()) {
            module?.createExcludedDir(outPath)
            return LocalFileSystem.getInstance().refreshAndFindFileByPath(outPath)
        }
        return null
    }

    /**
     * If the output path is the same as the directory the main file is in.
     */
    fun isMainFileParent(mainFile: VirtualFile?, project: Project): Boolean {
        return getOrCreateOutputPath(mainFile, project) == mainFile?.parent
    }

    /**
     * Copy subdirectories of the source directory to the output directory for includes to work in non-MiKTeX systems
     */
    @Throws(ExecutionException::class)
    fun updateOutputSubDirs(mainFile: VirtualFile?, project: Project) {
        val includeRoot = mainFile?.parent
        val outPath = resolve()?.path ?: return

        val files: Set<PsiFile>
        try {
            files = mainFile?.psiFile(project)?.referencedFileSet() ?: emptySet()
        }
        catch (e: IndexNotReadyException) {
            throw ExecutionException("Please wait until the indices are built.", e)
        }

        // Create output paths (see issue #70 on GitHub)
        files.asSequence()
            .mapNotNull { FileUtil.pathRelativeTo(includeRoot?.path ?: return@mapNotNull null, it.virtualFile.parent.path) }
            .forEach { File(outPath + it).mkdirs() }
    }
}