package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.NDListItemViewModel
import com.mirego.trikot.viewmodels.NDListViewModel
import com.mirego.trikot.viewmodels.factory.PropertyFactory
import org.reactivestreams.Publisher

open class NDMutableListViewModel<T : NDListItemViewModel> : NDMutableViewModel(), NDListViewModel<T> {
    override var elements: Publisher<List<T>> = PropertyFactory.create(listOf())
}
