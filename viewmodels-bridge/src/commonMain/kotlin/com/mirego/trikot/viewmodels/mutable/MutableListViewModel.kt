package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.viewmodels.ListItemViewModel
import com.mirego.trikot.viewmodels.ListViewModel

open class MutableListViewModel<T : ListItemViewModel> : NDMutableListViewModel<T>(),
    ListViewModel<T>
