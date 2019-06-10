package com.mirego.trikot.metaviews.mutable

import com.mirego.trikot.metaviews.MetaLabel
import com.mirego.trikot.metaviews.RichText
import com.mirego.trikot.metaviews.factory.PropertyFactory
import com.mirego.trikot.metaviews.properties.Color
import com.mirego.trikot.metaviews.properties.MetaSelector
import com.mirego.trikot.metaviews.resource.Font
import org.reactivestreams.Publisher

open class MutableMetaLabel : MutableMetaView(), MetaLabel {
    override var text = PropertyFactory.create("")

    override val richTexts: Publisher<List<RichText>>? = null

    override var fontFace = PropertyFactory.create(Font.None)

    override var fontSize = PropertyFactory.create(15)

    override var textColor = PropertyFactory.create(MetaSelector<Color>())
}
