package com.trikot.viewModels.sample.viewModels.home

import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.ViewModelAction
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.streams.reactive.just
import com.trikot.viewModels.sample.viewModels.ListItemViewModel
import com.trikot.viewModels.sample.viewModels.MutableHeaderListItemViewModel
import com.trikot.viewModels.sample.viewModels.MutableViewListItemViewModel
import com.trikot.viewModels.sample.navigation.NavigationDelegate

class ViewsViewModel(navigationDelegate: NavigationDelegate) : ListViewModel {
    override val items: List<ListItemViewModel> = listOf(
        MutableHeaderListItemViewModel(".backgroundColor"),
        MutableViewListItemViewModel().also {
            it.view.backgroundColor = StateSelector(Color(255, 0, 0)).just()
        },
        MutableHeaderListItemViewModel(".alpha"),
        MutableViewListItemViewModel().also {
            it.view.alpha = 0.5f.just()
        },
        MutableHeaderListItemViewModel(".hidden"),
        MutableViewListItemViewModel().also {
            it.view.hidden = true.just()
        },
        MutableHeaderListItemViewModel(".onTap"),
        MutableViewListItemViewModel().also {
            it.view.action = ViewModelAction { navigationDelegate.showAlert("Tapped $it") }.just()
        }
    )
}
