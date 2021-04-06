package com.mirego.trikot.viewmodels

import android.content.Context
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager

open class DefaultClusterFactory : ClusterFactory<DefaultClusterItem> {
    override fun createClusterManager(
        context: Context,
        googleMap: GoogleMap
    ): ClusterManager<DefaultClusterItem> {
        return ClusterManager<DefaultClusterItem>(context, googleMap).apply {
            setOnClusterItemClickListener { marker ->
                marker.onClick()
                true
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun createClusterItem(marker: MapViewModel.Marker): DefaultClusterItem {
        return DefaultClusterItem(
            LatLng(marker.position.latitude, marker.position.longitude),
            marker.label,
            marker.iconUrl,
            onClick = marker.onSelect
        )
    }
}
