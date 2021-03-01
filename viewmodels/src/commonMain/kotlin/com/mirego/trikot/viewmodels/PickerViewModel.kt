package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface PickerViewModel<T : PickerItemViewModel> : ViewModel {
    /**
     * List of elements in the spinner
     */
    val elements: Publisher<List<T>>

    /**
     * Appearing value on the spinner
     */
    val selectedValue: Publisher<Int>

    /**
     * Set the appearing value on the spinner
     */
    fun setSelectedValue(value: T)

    /**
     * If the spinner is enabled or disabled
     */
    val enabled: Publisher<Boolean>
}
