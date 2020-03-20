package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.MutableLabelViewModel
import com.mirego.trikot.viewModels.mutable.MutableViewModel
import com.mirego.trikot.streams.reactive.just

class MutableHeaderListItemViewModel(text: String, override var comparableId: String = "") : HeaderListItemViewModel, MutableViewModel() {
    override val text = MutableLabelViewModel().also {
        it.text = text.just()
    }
}
