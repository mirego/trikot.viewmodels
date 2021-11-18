package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface NDSliderViewModel : NDViewModel {
    /**
     * Minimum value of the slider
     */
    val minValue: Int
    /**
     * Maximum value of the slider
     */
    val maxValue: Int
    /**
     * Currently selected value on the slider
     */
    val selectedValue: Publisher<Int>

    /**
     * Set the value selected by the platform slider
     */
    fun setSelectedValue(value: Int)
}
