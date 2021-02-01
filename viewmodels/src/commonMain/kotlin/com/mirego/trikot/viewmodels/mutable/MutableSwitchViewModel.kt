package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.viewmodels.SwitchViewModel

open class MutableSwitchViewModel() : MutableViewModel(), SwitchViewModel {

    override val isOn = Publishers.behaviorSubject(false)

    override fun changeState(state: Boolean) {
        isOn.value = state
    }
}
