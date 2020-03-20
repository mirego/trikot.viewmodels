package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.MutableLabelViewModel
import com.mirego.trikot.viewModels.mutable.MutableViewModel

class MutableNavigableListItemViewModel(override var comparableId: String = "") : NavigableListItemViewModel, MutableViewModel() {
    override val title = MutableLabelViewModel()
}
