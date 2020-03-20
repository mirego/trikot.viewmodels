package com.mirego.trikot.viewModels.mutable

import com.mirego.trikot.viewModels.LabelViewModel
import com.mirego.trikot.viewModels.factory.PropertyFactory
import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.viewModels.text.RichText
import org.reactivestreams.Publisher

open class MutableLabelViewModel : MutableViewModel(), LabelViewModel {
    override var text = PropertyFactory.create("")

    override var richText: Publisher<RichText>? = null

    override var textColor = PropertyFactory.create(StateSelector<Color>())
}
