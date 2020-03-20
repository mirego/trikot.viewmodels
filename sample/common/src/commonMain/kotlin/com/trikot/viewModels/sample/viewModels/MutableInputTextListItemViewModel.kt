package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.MutableInputTextViewModel
import com.mirego.trikot.viewModels.mutable.MutableViewModel

class MutableInputTextListItemViewModel(override var comparableId: String = "") : InputTextListItemViewModel, MutableViewModel() {
    override val inputText = MutableInputTextViewModel()

    override fun isTheSame(other: ListItemViewModel): Boolean {
        return false
    }
}
