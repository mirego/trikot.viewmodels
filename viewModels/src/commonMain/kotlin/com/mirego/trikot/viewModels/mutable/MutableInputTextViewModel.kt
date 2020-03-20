package com.mirego.trikot.viewModels.mutable

import com.mirego.trikot.viewModels.InputTextViewModel
import com.mirego.trikot.viewModels.factory.PropertyFactory
import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.InputTextType
import com.mirego.trikot.streams.reactive.Publishers

open class MutableInputTextViewModel : MutableViewModel(), InputTextViewModel {
    override var userInput = Publishers.behaviorSubject("")

    override var inputType = PropertyFactory.create(InputTextType.TEXT)

    override var textColor = PropertyFactory.create(Color.None)

    override var placeholderText = PropertyFactory.create("")

    override fun setUserInput(value: String) {
        userInput.value = value
    }
}
