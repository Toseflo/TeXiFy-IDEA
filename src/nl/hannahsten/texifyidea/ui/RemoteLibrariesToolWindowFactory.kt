package nl.hannahsten.texifyidea.ui

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.openapi.application.ApplicationNamesInfo
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.dsl.builder.RightGap
import com.intellij.ui.dsl.builder.RowLayout
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.treeStructure.Tree
import nl.hannahsten.texifyidea.file.LatexFileType
import nl.hannahsten.texifyidea.remotelibraries.RemoteLibraryManager
import nl.hannahsten.texifyidea.structure.bibtex.BibtexStructureViewEntryElement
import nl.hannahsten.texifyidea.util.allFiles
import nl.hannahsten.texifyidea.util.hasLatexModule
import javax.swing.tree.DefaultMutableTreeNode

class RemoteLibrariesToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val librariesToolWindow = RemoteLibrariesToolWindow(project)
        val content = ContentFactory.SERVICE.getInstance().createContent(librariesToolWindow.content, "", false)
        toolWindow.contentManager.addContent(content)
    }

    override fun isApplicable(project: Project) =
        if (ApplicationNamesInfo.getInstance().scriptName == "idea") {
            project.hasLatexModule()
        }
        // Non-idea has no concept of modules so we need to use some other criterion based on the project
        else {
            project.allFiles(LatexFileType).isNotEmpty()
        }


    class RemoteLibrariesToolWindow(val project: Project) {

        val libraries = RemoteLibraryManager.getInstance().libraries.toMap().entries

        val rootNode = DefaultMutableTreeNode().apply {
            libraries.forEach { library ->
                val treeNode = DefaultMutableTreeNode(library.key).apply {
                    library.value.forEach { entry ->
                        val entryElement = BibtexStructureViewEntryElement(entry)
                        val entryNode = DefaultMutableTreeNode(entryElement)
                        add(entryNode)
                        entryElement.children.forEach {
                            entryNode.add(DefaultMutableTreeNode(it))
                        }
                    }
                }

                add(treeNode)
            }
        }


        val tree = Tree(rootNode).apply {
            isRootVisible = false

            setCellRenderer { tree, value, selected, expanded, leaf, row, hasFocus ->
                panel {
                    row {
                        when (val userObject = (value as DefaultMutableTreeNode).userObject) {
                            is StructureViewTreeElement -> {
                                icon(userObject.presentation.getIcon(true)!!).gap(RightGap.SMALL)
                                label(userObject.presentation.presentableText!!).gap(RightGap.SMALL)
                                label(userObject.presentation.locationString!!)
                            }
                            else -> label(value.toString())
                        }
                    }.layout(RowLayout.LABEL_ALIGNED)
                }
            }
        }

        val content = JBScrollPane(tree)
    }

}