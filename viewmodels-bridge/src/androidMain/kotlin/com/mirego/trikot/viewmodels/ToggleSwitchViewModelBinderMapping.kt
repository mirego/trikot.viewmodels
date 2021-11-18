package com.mirego.trikot.viewmodels

import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter

object ToggleSwitchViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        toggleSwitch: SwitchCompat,
        toggleSwitchViewModel: ToggleSwitchViewModel
    ) = ToggleSwitchViewModelBinder.bind(toggleSwitch, toggleSwitchViewModel)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        toggleSwitch: SwitchCompat,
        toggleSwitchViewModel: ToggleSwitchViewModel,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = ToggleSwitchViewModelBinder.bind(toggleSwitch, toggleSwitchViewModel, lifecycleOwnerWrapper)
}
