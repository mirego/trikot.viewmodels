package com.mirego.trikot.viewModels

import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.viewModels.properties.ViewModelAction
import org.reactivestreams.Publisher

interface ViewModel {
    /**
     * Alpha value of the view. 0.0 to 1.0
     */
    val alpha: Publisher<Float>
    /**
     * Background color of the view
     */
    val backgroundColor: Publisher<StateSelector<Color>>
    /**
     * Is view hidden or not. Should not take any place in the UI.
     * Should respect View.GONE Android behavior
     */
    val hidden: Publisher<Boolean>
    /**
     * Action to execute when the view is tapped
     */
    val action: Publisher<ViewModelAction>
}
