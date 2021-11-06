package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.ListItemViewModel

open class NDMutableListItemViewModel : NDMutableViewModel(), ListItemViewModel {
    override var comparableId = this.hashCode().toString()
}
