package com.mirego.trikot.metaviews

import com.mirego.trikot.metaviews.properties.Color
import com.mirego.trikot.metaviews.properties.MetaSelector
import com.mirego.trikot.metaviews.resource.Font
import org.reactivestreams.Publisher

interface MetaLabel : MetaView {
    /**
     * Label text
     */
    val text: Publisher<String>
    /**
     * Label font. FontManager should be configured by platform.
     */
    val fontFace: Publisher<Font>
    /**
     * Label font size
     */
    val fontSize: Publisher<Int>
    /**
     * Label text color
     */
    val textColor: Publisher<MetaSelector<Color>>
}