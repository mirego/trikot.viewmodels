package com.trikot.viewModels.sample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.trikot.viewModels.sample.navigation.NavigationDelegate
import com.trikot.viewModels.sample.viewModels.home.HomeViewModelImpl
import com.trikot.viewModels.sample.viewModels.home.ListViewModel

class AndroidAppViewModel(application: Application) : AndroidViewModel(application) {
    fun getVm(navigationDelegate: NavigationDelegate): ListViewModel = HomeViewModelImpl(navigationDelegate)
}
