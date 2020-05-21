package com.mirego.trikot.viewmodels

import com.mirego.trikot.foundation.CommonJSExport
import com.mirego.trikot.viewmodels.properties.Color
import com.mirego.trikot.viewmodels.properties.StateSelector
import com.mirego.trikot.viewmodels.text.RichText
import org.reactivestreams.Publisher
import kotlin.js.JsName

@CommonJSExport
interface LabelViewModel : ViewModel {
    /**
     * Label text
     */
    @JsName("text")
    val text: Publisher<String>
    /**
      * Label rich text. Use this instead of {@link #text text} when not null
     */
    @JsName("richText")
    val richText: Publisher<RichText>?
    /**
     * Label text color
     */
    @JsName("textColor")
    val textColor: Publisher<StateSelector<Color>>
}
