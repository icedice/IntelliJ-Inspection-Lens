package com.chylex.intellij.inspectionlens.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import java.awt.Color

/**
 * Supports storing the application settings in a persistent way.
 * The [State] and [Storage] annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
    name = "InspectionlensSettings",
    storages = [Storage("InspectionLensPlugin.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState?> {



    val colors = mutableMapOf("foreground" to Color.red)

    var prefix = "< "
   // var fgColr: Color = Color.white
    override fun getState(): AppSettingsState? {
        return this
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: AppSettingsState
            get() = ApplicationManager.getApplication().getService(
                AppSettingsState::class.java
            )
    }
}