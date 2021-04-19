package com.mirego.trikot.viewmodels

import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.mirego.trikot.streams.reactive.just
import com.mirego.trikot.streams.reactive.observe
import com.mirego.trikot.viewmodels.mutable.MutableToggleSwitchViewModel

object ToggleSwitchViewModelBinder {
    private val noSwitchViewModel =
        MutableToggleSwitchViewModel().apply { hidden = true.just() } as ToggleSwitchViewModel

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        toggleSwitch: SwitchCompat,
        toggleSwitchViewModel: ToggleSwitchViewModel,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        toggleSwitch.bindViewModel(toggleSwitchViewModel, lifecycleOwnerWrapper)

        toggleSwitchViewModel.isOn.observe(lifecycleOwnerWrapper.lifecycleOwner) { isOn ->
            if (toggleSwitch.isChecked != isOn) {
                toggleSwitch.isChecked = isOn
            }
        }

        toggleSwitchViewModel.isEnabled.observe(lifecycleOwnerWrapper.lifecycleOwner) { isEnabled ->
            toggleSwitch.isEnabled = isEnabled
        }
    }
}
