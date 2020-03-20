package com.mirego.trikot.viewModels

import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.viewModels.text.RichText
import org.reactivestreams.Publisher

interface LabelViewModel : ViewModel {
    /**
     * Label text
     */
    val text: Publisher<String>
    /**
      * Label rich text. Use this instead of {@link #text text} when not null
     */
    val richText: Publisher<RichText>?
    /**
     * Label text color
     */
    val textColor: Publisher<StateSelector<Color>>
}
