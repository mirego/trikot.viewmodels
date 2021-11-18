package com.mirego.trikot.viewmodels

import android.widget.EditText
import androidx.databinding.BindingAdapter

object InputTextViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        editText: EditText,
        InputTextViewModel: InputTextViewModel?
    ) = InputTextViewModelBinder.bind(editText, InputTextViewModel)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        editText: EditText,
        InputTextViewModel: InputTextViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = InputTextViewModelBinder.bind(editText, InputTextViewModel, lifecycleOwnerWrapper)
}
