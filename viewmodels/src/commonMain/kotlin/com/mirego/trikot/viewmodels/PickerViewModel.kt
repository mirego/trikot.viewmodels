package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface PickerViewModel<T> : ViewModel {
    /**
     * List of elements in the spinner
     */
    val elements: Publisher<List<PickerItemViewModel<T>>>

    /**
     * Appearing value on the spinner
     */
    val selectedValueIndex: Publisher<Int>

    /**
     * Set the appearing value on the spinner
     */
    fun setSelectedValueIndex(index: Int)

    /**
     * If the spinner is enabled or disabled
     */
    val enabled: Publisher<Boolean>
}
