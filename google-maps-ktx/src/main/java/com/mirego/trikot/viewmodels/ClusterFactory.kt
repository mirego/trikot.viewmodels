package com.mirego.trikot.viewmodels

import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

interface ClusterFactory<T : ClusterItem> {
    fun createClusterManager(mapView: MapView, googleMap: GoogleMap): ClusterManager<T>
    fun createClusterItem(marker: MapViewModel.Marker): T
}
