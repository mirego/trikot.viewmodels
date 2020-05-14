package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.viewmodels.SliderViewModel
import org.reactivestreams.Publisher

open class MutableSliderViewModel(
    override val minValue: Int = 0,
    override val maxValue: Int = 5,
    override val stepValue: Int = 1
) : MutableViewModel(), SliderViewModel {
    private val internalPublisher = Publishers.behaviorSubject(minValue)

    override val selectedValue: Publisher<Int> = internalPublisher

    override fun setSelectedValue(value: Int) {
        internalPublisher.value = value
    }
}
