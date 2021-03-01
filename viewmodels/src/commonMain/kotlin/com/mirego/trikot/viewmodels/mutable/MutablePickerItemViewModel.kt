package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.PickerItemViewModel

open class MutablePickerItemViewModel : MutableViewModel(), PickerItemViewModel {
    override var comparableId = this.hashCode().toString()
    override var displayName = ""
}
