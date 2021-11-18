package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.PickerItemViewModel

open class MutablePickerItemViewModel<T>(override var displayName: String, override var value: T) : NDMutablePickerItemViewModel<T>(displayName, value), PickerItemViewModel<T> {
    override var comparableId = this.hashCode().toString()
}