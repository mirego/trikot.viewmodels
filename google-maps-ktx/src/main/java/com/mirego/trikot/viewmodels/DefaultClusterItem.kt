package com.mirego.trikot.viewmodels

import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class DefaultClusterItem(
    val latLng: LatLng,
    val textLabel: String? = null,
    val iconUrl: String? = null,
    val myTitle: String? = null,
    val mySnippet: String? = null,
    val onClick: () -> Unit
) : ClusterItem {
    override fun getPosition() = latLng
    override fun getTitle() = myTitle
    override fun getSnippet() = mySnippet
}
