package com.mirego.trikot.viewModels.factory

import com.mirego.trikot.streams.reactive.Publishers
import org.reactivestreams.Publisher

object PropertyFactory {
    fun <T> create(value: T): Publisher<T> = Publishers.just(value)
}
