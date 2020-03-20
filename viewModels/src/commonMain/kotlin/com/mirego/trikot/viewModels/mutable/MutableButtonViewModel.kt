package com.mirego.trikot.viewModels.mutable

import com.mirego.trikot.viewModels.ButtonViewModel
import com.mirego.trikot.viewModels.factory.PropertyFactory
import com.mirego.trikot.viewModels.properties.Alignment
import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.viewModels.resource.ImageResource

open class MutableButtonViewModel : MutableLabelViewModel(), ButtonViewModel {
    override var backgroundImageResource = PropertyFactory.create(StateSelector<ImageResource>())

    override var enabled = PropertyFactory.create(true)

    override var imageAlignment = PropertyFactory.create(Alignment.CENTER)

    override var imageResource = PropertyFactory.create(StateSelector<ImageResource>())

    override var selected = PropertyFactory.create(false)

    override var tintColor = PropertyFactory.create(StateSelector<Color>())
}
