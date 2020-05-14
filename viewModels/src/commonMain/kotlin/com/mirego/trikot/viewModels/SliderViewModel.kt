package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface SliderViewModel: ViewModel {
    val minValue: Int
    val maxValue: Int
    val stepValue: Int
    val selectedValue: Publisher<Int>

    fun setSelectedValue(value: Int)
}
