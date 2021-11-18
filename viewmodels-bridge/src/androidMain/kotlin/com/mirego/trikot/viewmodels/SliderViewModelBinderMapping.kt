package com.mirego.trikot.viewmodels

import android.widget.SeekBar
import androidx.databinding.BindingAdapter

object SliderViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        seekBar: SeekBar,
        sliderViewModel: SliderViewModel?
    ) = SliderViewModelBinder.bind(seekBar, sliderViewModel)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        seekBar: SeekBar,
        sliderViewModel: SliderViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = SliderViewModelBinder.bind(seekBar, sliderViewModel, lifecycleOwnerWrapper)
}
