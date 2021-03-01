package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.streams.reactive.promise.Promise
import com.mirego.trikot.viewmodels.PickerItemViewModel
import com.mirego.trikot.viewmodels.PickerViewModel
import com.mirego.trikot.viewmodels.factory.PropertyFactory
import org.reactivestreams.Publisher

open class MutablePickerViewModel<T : PickerItemViewModel> :
    MutableViewModel(), PickerViewModel<T> {

    override val selectedValue = Publishers.behaviorSubject(0)

    override fun setSelectedValue(value: T) {
        Promise.from(elements).onSuccess { selectedValue.value = it.indexOf(value) }
    }

    override val elements: Publisher<List<T>> = PropertyFactory.create(listOf())

    override var enabled = PropertyFactory.never<Boolean>()
}
