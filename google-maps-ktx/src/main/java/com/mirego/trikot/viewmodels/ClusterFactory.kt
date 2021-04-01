package com.mirego.trikot.viewmodels

import android.content.Context
import com.google.android.libraries.maps.GoogleMap
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

interface ClusterFactory<T : ClusterItem> {
    fun createClusterManager(context: Context, googleMap: GoogleMap): ClusterManager<T>
    fun createClusterItem(
        position: MapViewModel.Position,
        textLabel: String?,
        iconUrl: String?,
        onClick: () -> Unit
    ): T
}
