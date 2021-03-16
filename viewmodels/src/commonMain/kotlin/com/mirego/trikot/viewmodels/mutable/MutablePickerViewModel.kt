package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.viewmodels.PickerItemViewModel
import com.mirego.trikot.viewmodels.PickerViewModel
import com.mirego.trikot.viewmodels.factory.PropertyFactory
import org.reactivestreams.Publisher

open class MutablePickerViewModel<T> : PickerViewModel<T> {
    override val selectedValueIndex = Publishers.behaviorSubject(0)

    override fun setSelectedValueIndex(index: Int) {
        selectedValueIndex.value = index
    }

    override var elements: Publisher<List<PickerItemViewModel<T>>> = PropertyFactory.create(
        listOf()
    )

    override var enabled = Publishers.behaviorSubject(true)
}
