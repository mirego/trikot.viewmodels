package com.mirego.trikot.viewmodels

import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.mirego.trikot.streams.reactive.just
import com.mirego.trikot.streams.reactive.observe
import com.mirego.trikot.viewmodels.mutable.MutableToggleSwitchViewModel

object ToggleViewModelBinder {
    private val noSwitchViewModel =
        MutableToggleSwitchViewModel().apply { hidden = true.just() } as ToggleSwitchViewModel

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        toggle: SwitchCompat,
        toggleViewModel: ToggleSwitchViewModel,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        toggle.bindViewModel(toggleViewModel, lifecycleOwnerWrapper)

        toggleViewModel.isOn.observe(lifecycleOwnerWrapper.lifecycleOwner) { isOn ->
            if (toggle.isChecked != isOn) {
                toggle.isChecked = isOn
            }
        }

        toggleViewModel.isEnabled.observe(lifecycleOwnerWrapper.lifecycleOwner) { isEnabled ->
            toggle.isEnabled = isEnabled
        }
    }
}
