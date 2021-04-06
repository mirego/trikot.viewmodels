package com.mirego.trikot.viewmodels

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.lifecycle.coroutineScope
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.LatLngBounds
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.ktx.CameraIdleEvent
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.cameraEvents
import com.mirego.trikot.streams.reactive.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

object MapViewModelBinder {

    const val MAP_VIEW_BUNDLE_KEY = "mapView.bundle"

    @ExperimentalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    @SuppressLint("MissingPermission")
    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper", "clusterFactory")
    fun bind(
        mapView: MapView,
        mapViewModel: MapViewModel,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper,
        clusterFactory: ClusterFactory<*>
    ) {
        val lifecycleOwner = lifecycleOwnerWrapper.lifecycleOwner
        val lifecycle = lifecycleOwner.lifecycle

        lifecycle.coroutineScope.launchWhenCreated {
            val googleMap = mapView.awaitMap()
            val clusterManager = clusterFactory.createClusterManager(
                mapView.context,
                googleMap
            ) as ClusterManager<ClusterItem>

            mapViewModel.minimumZoomLevel.let(googleMap::setMinZoomPreference)

            CameraUpdateFactory.newLatLngZoom(
                mapViewModel.initialCamera.targets.first().asLatLng(),
                mapViewModel.initialCamera.zoom!!
            ).let(googleMap::moveCamera)

            googleMap.uiSettings.apply {
                isMyLocationButtonEnabled = false
                isMapToolbarEnabled = false
            }

            mapViewModel.isInteractive.observe(lifecycleOwner) { isInteractive ->
                googleMap.uiSettings.setAllGesturesEnabled(isInteractive)
            }

            mapViewModel.shouldShowUserMarker.observe(lifecycleOwner) {
                googleMap.isMyLocationEnabled = it
            }

            mapViewModel.camera.observe(lifecycleOwner) { camera ->
                if (camera == MapViewModel.Camera.none) {
                    return@observe
                }

                camera.targets.takeUnless { it.isEmpty() }?.let { targets ->
                    when (targets.size) {
                        1 -> googleMap.focusOnSingleLocation(
                            targets.first().asLatLng(),
                            camera.zoom
                        )
                        else -> googleMap.focusOnBoundingBox(
                            targets.asLatLngBounds(),
                            mapView.width.div(4)
                        )
                    }
                }
            }

            mapViewModel.markers.observe(lifecycleOwner) { markers ->
                val items = markers.map {
                    clusterFactory.createClusterItem(it)
                }

                clusterManager.apply {
                    clearItems()
                    addItems(items)
                    cluster()
                }
            }

            val onMapPositionChange = {
                googleMap.cameraPosition.let { cameraPosition ->
                    val target = cameraPosition.target
                    val position = MapViewModel.Position(target.latitude, target.longitude)
                    val zoom = cameraPosition.zoom

                    val camera = MapViewModel.Camera(targets = listOf(position), zoom = zoom)
                    mapViewModel.didChangeCamera(camera)
                }
            }

            // Must be at the end of the block
            googleMap.cameraEvents().collect { event ->
                when (event) {
                    is CameraIdleEvent -> {
                        clusterManager.onCameraIdle()
                        onMapPositionChange()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun GoogleMap.focusOnSingleLocation(
        location: LatLng,
        zoom: Float? = null,
        callback: GoogleMap.CancelableCallback? = null
    ) {
        animateCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.Builder()
                    .target(location)
                    .zoom(zoom ?: cameraPosition.zoom)
                    .build()
            ),
            callback
        )
    }

    private fun GoogleMap.focusOnBoundingBox(
        bounds: LatLngBounds,
        padding: Int,
        callback: GoogleMap.CancelableCallback? = null
    ) {
        animateCamera(
            CameraUpdateFactory.newLatLngBounds(bounds, padding),
            callback
        )
    }

    private fun MapViewModel.Position.asLatLng(): LatLng {
        return LatLng(latitude, longitude)
    }

    private fun List<MapViewModel.Position>.asLatLngBounds(): LatLngBounds {
        return LatLngBounds.builder().also { builder ->
            forEach { position ->
                builder.include(position.asLatLng())
            }
        }.build()
    }

    fun Collection<ClusterItem>.asLatLngBounds(): LatLngBounds {
        return LatLngBounds.builder().also { builder ->
            forEach { item ->
                builder.include(item.position)
            }
        }.build()
    }
}
