package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.mutable.ImageFlowProvider
import com.mirego.trikot.viewModels.mutable.MutableImageViewModel
import com.mirego.trikot.viewModels.mutable.MutableViewModel

class MutableImageListItemViewModel(imageFlowProvider: ImageFlowProvider, override var comparableId: String = "") : ImageListItemViewModel, MutableViewModel() {
    override val image = MutableImageViewModel(imageFlowProvider)
}
