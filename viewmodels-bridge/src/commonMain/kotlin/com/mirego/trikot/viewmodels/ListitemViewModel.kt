package com.mirego.trikot.viewmodels

interface ListItemViewModel : NDListItemViewModel, ViewModel {
    fun isTheSame(other: ListItemViewModel): Boolean {
        return comparableId == other.comparableId
    }

    fun haveTheSameContent(other: ListItemViewModel): Boolean {
        return true
    }
}
