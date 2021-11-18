package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.NDPickerItemViewModel

open class NDMutablePickerItemViewModel<T>(override var displayName: String, override var value: T) : NDMutableViewModel(), NDPickerItemViewModel<T> {
    override var comparableId = this.hashCode().toString()
}
