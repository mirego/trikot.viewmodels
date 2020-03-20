package com.trikot.viewModels.sample.viewModels

import com.mirego.trikot.viewModels.ViewModel

interface ListItemViewModel : ViewModel {
    var comparableId: String
    fun isTheSame(other: ListItemViewModel): Boolean {
        return comparableId == other.comparableId
    }
    fun haveTheSameContent(other: ListItemViewModel): Boolean {
        return true
    }
}
