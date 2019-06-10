package com.mirego.trikot.metaviews

import com.mirego.trikot.metaviews.resource.Font

interface RichText {
    val text: String
    val style: Style
    val font: Font

    enum class Style {
        NORMAL,
        BOLD,
        ITALIC,
        UNDERLINE
    }
}
