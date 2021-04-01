package com.mirego.trikot.viewmodels

import org.reactivestreams.Publisher

interface MapViewModel : ViewModel {
    val minimumZoomLevel: Float
    val initialCamera: Camera
    fun didChangeCamera(camera: Camera)

    val isInteractive: Publisher<Boolean>
    val shouldShowUserMarker: Publisher<Boolean>
    val markers: Publisher<List<Marker>>
    val camera: Publisher<Camera>

    data class Position(
        val latitude: Double,
        val longitude: Double
    ) {
        companion object {
            val none = Position(0.0, 0.0)
        }
    }

    data class Marker(
        val position: Position,
        val label: String? = null,
        val iconUrl: String? = null,
        val onSelect: () -> Unit = {}
    )

    data class Camera(
        val targets: List<Position>,
        val zoom: Float? = null
    ) {
        companion object {
            val none = Camera(emptyList())
        }
    }
}
