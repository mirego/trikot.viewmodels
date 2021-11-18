package com.mirego.trikot.viewmodels

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object ListViewModelBinderMapping {

    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        recyclerView: RecyclerView,
        listViewModel: ListViewModel<*>?
    ) = ListViewModelBinder.bind(recyclerView, listViewModel)

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bindList(
        view: RecyclerView,
        listViewModel: ListViewModel<*>?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = ListViewModelBinder.bindList(view, listViewModel, lifecycleOwnerWrapper)
}
