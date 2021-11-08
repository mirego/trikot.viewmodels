package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.NDButtonViewModel
import com.mirego.trikot.viewmodels.factory.PropertyFactory
import com.mirego.trikot.viewmodels.properties.Alignment
import com.mirego.trikot.viewmodels.properties.Color
import com.mirego.trikot.viewmodels.properties.StateSelector
import com.mirego.trikot.viewmodels.resource.ImageResource

open class NDMutableButtonViewModel : NDMutableLabelViewModel(), NDButtonViewModel {
    override var backgroundImageResource = PropertyFactory.create(StateSelector<ImageResource>())

    override var enabled = PropertyFactory.create(true)

    override var imageAlignment = PropertyFactory.create(Alignment.CENTER)

    override var imageResource = PropertyFactory.create(StateSelector<ImageResource>())

    override var selected = PropertyFactory.create(false)

    override var tintColor = PropertyFactory.create(StateSelector<Color>())
}
