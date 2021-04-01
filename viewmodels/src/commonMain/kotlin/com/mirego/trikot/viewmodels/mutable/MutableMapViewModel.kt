package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.viewmodels.MapViewModel
import com.mirego.trikot.viewmodels.factory.PropertyFactory
import org.reactivestreams.Publisher

open class MutableMapViewModel : MutableViewModel(), MapViewModel {
    override val minimumZoomLevel: Float = 3F
    override val initialCamera: MapViewModel.Camera get() = MapViewModel.Camera.none
    override val isInteractive: Publisher<Boolean> = PropertyFactory.create(true)
    override val shouldShowUserMarker: Publisher<Boolean> = PropertyFactory.create(false)
    override val markers: Publisher<List<MapViewModel.Marker>> = PropertyFactory.create(listOf())
    override val camera: Publisher<MapViewModel.Camera> = Publishers.never()

    override fun didChangeCamera(camera: MapViewModel.Camera) {
    }
}
