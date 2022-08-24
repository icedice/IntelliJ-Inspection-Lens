package com.chylex.intellij.inspectionlens.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String? {
        return "SDK: Application Settings Example"
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent? {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        var modified = mySettingsComponent!!.userNameText != settings.prefix
        modified = modified or (mySettingsComponent!!.foregroundColor.selectedColor != settings.colors["foreground"])
        return modified
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.prefix = mySettingsComponent!!.userNameText

       settings.colors["foreground"] = mySettingsComponent!!.foregroundColor.selectedColor!!
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        mySettingsComponent!!.userNameText = settings.prefix
        mySettingsComponent!!.foregroundColor.setSelectedColor(settings.colors["foreground"])
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}