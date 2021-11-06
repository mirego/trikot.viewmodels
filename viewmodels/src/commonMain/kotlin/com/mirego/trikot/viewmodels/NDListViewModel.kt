package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface NDListViewModel<T : NDListItemViewModel> : NDViewModel {
    val elements: Publisher<List<T>>
}
