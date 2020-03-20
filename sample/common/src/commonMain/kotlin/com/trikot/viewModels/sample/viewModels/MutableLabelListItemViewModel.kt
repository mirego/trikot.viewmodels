package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.MutableLabelViewModel
import com.mirego.trikot.viewModels.mutable.MutableViewModel

class MutableLabelListItemViewModel(override var comparableId: String = "") : LabelListItemViewModel, MutableViewModel() {
    override val label = MutableLabelViewModel()
}
