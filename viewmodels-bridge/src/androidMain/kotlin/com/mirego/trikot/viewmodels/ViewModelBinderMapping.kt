package com.mirego.trikot.viewmodels

import android.view.View
import androidx.databinding.BindingAdapter

object ViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model")
    fun bindViewModel(
        view: View,
        viewModel: ViewModel?
    ) {
        view.bindViewModel(viewModel)
    }

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bindViewModel(
        view: View,
        viewModel: ViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        view.bindViewModel(viewModel, lifecycleOwnerWrapper)
    }
}
