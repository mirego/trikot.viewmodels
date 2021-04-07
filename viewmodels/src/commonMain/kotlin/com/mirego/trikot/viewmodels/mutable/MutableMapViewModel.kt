package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.viewmodels.MapViewModel
import com.mirego.trikot.viewmodels.factory.PropertyFactory

open class MutableMapViewModel : MutableViewModel(), MapViewModel {
    override val userSetCamera = Publishers.behaviorSubject(MapViewModel.Camera.none)
    override val minimumZoomLevel = PropertyFactory.create(3F)
    override val isInteractive = PropertyFactory.create(true)
    override val shouldShowUserMarker = PropertyFactory.create(false)
    override val markers = PropertyFactory.create(listOf<MapViewModel.Marker>())
    override val camera = Publishers.never<MapViewModel.Camera>()

    override fun didChangeCamera(camera: MapViewModel.Camera) {
        userSetCamera.value = camera
    }
}
