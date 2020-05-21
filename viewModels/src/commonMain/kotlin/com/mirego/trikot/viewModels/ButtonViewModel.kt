package com.mirego.trikot.viewmodels

import com.mirego.trikot.foundation.CommonJSExport
import com.mirego.trikot.viewmodels.properties.Alignment
import com.mirego.trikot.viewmodels.properties.Color
import com.mirego.trikot.viewmodels.properties.StateSelector
import com.mirego.trikot.viewmodels.resource.ImageResource
import org.reactivestreams.Publisher
import kotlin.js.JsName

@CommonJSExport
interface ButtonViewModel : LabelViewModel {
    /**
     * Resource for the background image of the button
     */
    @JsName("backgroundImageResource")
    val backgroundImageResource: Publisher<StateSelector<ImageResource>>
    /**
     * If the button is enabled or disabled
     */
    @JsName("enabled")
    val enabled: Publisher<Boolean>
    /**
     * Position of the image related to the text
     */
    @JsName("imageAlignment")
    val imageAlignment: Publisher<Alignment>
    /**
     * Ressource of the button image. Can be associated with a text
     */
    @JsName("imageResource")
    val imageResource: Publisher<StateSelector<ImageResource>>
    /**
     * Selected state of the view
     */
    @JsName("selected")
    val selected: Publisher<Boolean>
    /**
     * ImageResource tint color
     */
    @JsName("tintColor")
    val tintColor: Publisher<StateSelector<Color>>
}
