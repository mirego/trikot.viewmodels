package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.MutableViewModel

class MutableViewListItemViewModel(override var comparableId: String = "") : ViewListItemViewModel, MutableViewModel() {
    override val view = MutableViewModel()
}
