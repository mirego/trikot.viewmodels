package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.ImageViewModel

open class MutableImageViewModel(imageFlowProvider: ImageFlowProvider) :
    NDMutableImageViewModel(imageFlowProvider), ImageViewModel
