package com.mirego.trikot.viewmodels

import android.widget.Spinner
import androidx.databinding.BindingAdapter

object PickerViewModelBinderMapping {

    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        spinner: Spinner,
        pickerViewModel: PickerViewModel<*>?
    ) = PickerViewModelBinder.bind(spinner, pickerViewModel)

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        picker: Spinner,
        pickerViewModel: PickerViewModel<*>?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = PickerViewModelBinder.bind(picker, pickerViewModel, lifecycleOwnerWrapper)
}
