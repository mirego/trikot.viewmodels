package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface SwitchViewModel : ViewModel {
    /**
     * Currently selected state of the view
     */
    val isOn: Publisher<Boolean>

    /**
     * Set the state defined by the platform switch
     */
    fun changeState(state: Boolean)
}
