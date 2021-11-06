package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface ListViewModel<T : ListItemViewModel> : NDViewModel {
    val elements: Publisher<List<T>>
}
