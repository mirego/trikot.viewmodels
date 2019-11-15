package com.trikot.metaviews.sample.navigation

import com.trikot.metaviews.sample.viewmodels.home.LabelsViewModel
import com.trikot.metaviews.sample.viewmodels.home.ListViewModel
import com.trikot.metaviews.sample.viewmodels.home.ViewsViewModel
import com.trikot.metaviews.sample.viewmodels.home.ButtonsViewModel

interface NavigationDelegate {
    fun navigateTo(destination: Destination)
    fun showAlert(text: String)
}

enum class Destination(val getViewModel: (NavigationDelegate) -> ListViewModel) {
    VIEWS({ ViewsViewModel(it) } ),
    LABELS({ LabelsViewModel(it) } ),
    BUTTONS({ ButtonsViewModel(it) } )
}
