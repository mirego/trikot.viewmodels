package com.mirego.trikot.viewmodels.text

import com.mirego.trikot.viewmodels.properties.Color
import com.mirego.trikot.viewmodels.resource.TextAppearanceResource
import kotlin.js.JsExport

@JsExport
data class RichText(val text: String, val ranges: List<RichTextRange>)

@JsExport
data class RichTextRange(val range: IntRange, val transform: RichTextTransform)

@JsExport
sealed class RichTextTransform

data class StyleTransform(val style: Style) : RichTextTransform() {
    enum class Style {
        NORMAL,
        BOLD,
        ITALIC,
        BOLD_ITALIC,
        UNDERLINE
    }
}

@JsExport
data class ColorTransform(val color: Color) : RichTextTransform()

@JsExport
data class TextAppearanceResourceTransform(val textAppearanceResource: TextAppearanceResource) :
    RichTextTransform()
