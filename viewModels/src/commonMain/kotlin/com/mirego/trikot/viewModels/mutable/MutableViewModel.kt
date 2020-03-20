package com.mirego.trikot.viewModels.mutable

import com.mirego.trikot.viewModels.ViewModel
import com.mirego.trikot.viewModels.factory.PropertyFactory
import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.viewModels.properties.ViewModelAction

open class MutableViewModel : ViewModel {
    override var alpha = PropertyFactory.create(1f)

    override var backgroundColor = PropertyFactory.create(StateSelector<Color>())

    override var hidden = PropertyFactory.create(false)

    override var action = PropertyFactory.create(ViewModelAction.None)
}
