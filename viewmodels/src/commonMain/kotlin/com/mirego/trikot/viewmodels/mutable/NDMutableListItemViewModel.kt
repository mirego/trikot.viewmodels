package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.NDListItemViewModel

open class NDMutableListItemViewModel : NDMutableViewModel(), NDListItemViewModel {
    override var comparableId = this.hashCode().toString()
}
