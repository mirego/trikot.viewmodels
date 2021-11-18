package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.viewmodels.NDSliderViewModel
import org.reactivestreams.Publisher

open class NDMutableSliderViewModel(
    final override val minValue: Int = 0,
    final override val maxValue: Int = 5,
    initialValue: Int = minValue
) : NDMutableViewModel(), NDSliderViewModel {
    private val internalPublisher = Publishers.behaviorSubject(
        if (initialValue in minValue..maxValue) initialValue else minValue
    )

    override val selectedValue: Publisher<Int> = internalPublisher

    override fun setSelectedValue(value: Int) {
        internalPublisher.value = value
    }
}
