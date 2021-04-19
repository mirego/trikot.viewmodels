package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface ToggleSwitchViewModel : ViewModel {
    /**
     * Currently selected state of the view
     */
    val isOn: Publisher<Boolean>

    /**
     * If the ToggleSwitch is enabled or disabled
     */
    val isEnabled: Publisher<Boolean>
}
