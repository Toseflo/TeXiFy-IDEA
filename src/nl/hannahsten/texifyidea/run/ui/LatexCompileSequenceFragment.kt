package nl.hannahsten.texifyidea.run.ui

import com.intellij.execution.impl.RunnerAndConfigurationSettingsImpl
import com.intellij.execution.ui.RunConfigurationEditorFragment
import com.intellij.util.ui.JBUI
import nl.hannahsten.texifyidea.run.LatexRunConfiguration
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.image.ColorModel
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class LatexCompileSequenceFragment(private val component: LatexCompileSequenceComponent, commandLinePosition: Int)
    : RunConfigurationEditorFragment<LatexRunConfiguration, JComponent>(
        "compileSequence", null, null, wrap(component), commandLinePosition, { true }
    ) {

    companion object {

        private fun wrap(component: LatexCompileSequenceComponent): JComponent {
            val panel = JPanel(BorderLayout())
            val label = JLabel("Compile sequence").apply {
                font = JBUI.Fonts.label().deriveFont(Font.BOLD)
            }
            val hintLabel = JLabel("Double-click a step to edit").apply {
                font = JBUI.Fonts.label().deriveFont(Font.ITALIC)
                foreground = Color.LIGHT_GRAY
            }
            panel.add(label, BorderLayout.NORTH)
            panel.add(component, BorderLayout.CENTER)
            panel.add(hintLabel, BorderLayout.SOUTH)

            return panel
        }
    }

    init {
        component.changeListener = { fireEditorStateChanged() }
        actionHint = "Specify steps needed for compiling the document"
    }

    // Confirm the changes, i.e. copy current UI state into the target settings object.
    override fun applyEditorTo(s: RunnerAndConfigurationSettingsImpl) {
        component.applyEditorTo(s.configuration as LatexRunConfiguration)
    }

    // Discard all non-confirmed user changes made via the UI
    override fun doReset(s: RunnerAndConfigurationSettingsImpl) {
        component.resetEditorFrom(s.configuration as LatexRunConfiguration)
    }
}
