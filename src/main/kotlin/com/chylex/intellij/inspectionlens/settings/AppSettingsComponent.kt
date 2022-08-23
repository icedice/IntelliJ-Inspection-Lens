package com.chylex.intellij.inspectionlens.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val myUserNameText = JBTextField()
    var foregroundColor = ColorPanel()

    init {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Message prefix: "), myUserNameText, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("John: "), foregroundColor, 1, false)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = myUserNameText
    var userNameText: String
        get() = myUserNameText.text
        set(newText) {
            myUserNameText.text = newText
        }
}