package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.MutableButtonViewModel
import com.mirego.trikot.viewModels.mutable.MutableViewModel

class MutableButtonListItemViewModel(override var comparableId: String = "") : ButtonListItemViewModel, MutableViewModel() {
    override val button = MutableButtonViewModel()

    override fun isTheSame(other: ListItemViewModel): Boolean {
        return false
    }
}
