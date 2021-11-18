package com.mirego.trikot.viewmodels

interface NDPickerItemViewModel<T> {
    var comparableId: String
    var displayName: String
    var value: T
}
