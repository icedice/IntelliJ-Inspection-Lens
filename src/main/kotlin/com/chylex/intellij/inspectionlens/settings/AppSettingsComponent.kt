package com.chylex.intellij.inspectionlens.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class AppSettingsComponent {
    val panel: JPanel
    private val prefixTextField = JBTextField()
    var foregroundColor = ColorPanel()

    init {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Message prefix: "), prefixTextField, 1, false)

            .addLabeledComponent(JBLabel("John: "), foregroundColor, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = prefixTextField
    var userNameText: String
        get() = prefixTextField.text
        set(newText) {
            prefixTextField.text = newText
        }
}