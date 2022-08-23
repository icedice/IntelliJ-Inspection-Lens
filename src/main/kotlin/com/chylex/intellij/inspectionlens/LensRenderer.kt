package com.chylex.intellij.inspectionlens

import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInsight.daemon.impl.HintRenderer
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.Color

/**
 * Renders the text of an inspection lens.
 */
class LensRenderer(info: HighlightInfo) : HintRenderer(null) {
	private lateinit var severity: LensSeverity
	
	init {
		setPropertiesFrom(info)
	}
	
	fun setPropertiesFrom(info: HighlightInfo) {
		text = "< \t" + getValidDescriptionText(info.description)
		severity = LensSeverity.from(info.severity)
	}
	
	override fun paint(inlay: Inlay<*>, g: Graphics, r: Rectangle, textAttributes: TextAttributes) {
		fixBaselineForTextRendering(r)
		super.paint(inlay, g, r, textAttributes)
	}
	
	override fun getTextAttributes(editor: Editor): TextAttributes {
		return ATTRIBUTES_SINGLETON.also {
			it.backgroundColor = severity.getColor(editor)
			it.foregroundColor = Color(200, 200, 200)
		}
	}
	
	override fun useEditorFont(): Boolean {
		return true
	}
	
	private companion object {
		private val ATTRIBUTES_SINGLETON = TextAttributes(null, null, null, null, Font.ITALIC)
		
		private fun getValidDescriptionText(text: String?): String {
			return if (text.isNullOrBlank()) " " else addMissingPeriod(text)
		}
		
		private fun addMissingPeriod(text: String): String {
			return if (text.endsWith('.')) text else "$text."
		}
		
		private fun fixBaselineForTextRendering(rect: Rectangle) {
			rect.y += 1
		}
	}
}
