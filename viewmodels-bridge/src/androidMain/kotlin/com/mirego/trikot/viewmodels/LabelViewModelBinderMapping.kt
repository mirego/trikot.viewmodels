package com.mirego.trikot.viewmodels

import android.widget.TextView
import androidx.databinding.BindingAdapter

object LabelViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        textView: TextView,
        labelViewModel: LabelViewModel?
    ) = LabelViewModelBinder.bind(textView, labelViewModel)

    @JvmStatic
    @BindingAdapter("view_model", "hiddenVisibility", "lifecycleOwnerWrapper")
    fun bind(
        textView: TextView,
        labelViewModel: LabelViewModel?,
        hiddenVisibility: HiddenVisibility,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = LabelViewModelBinder.bind(textView, labelViewModel, hiddenVisibility, lifecycleOwnerWrapper)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        textView: TextView,
        labelViewModel: LabelViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = LabelViewModelBinder.bind(textView, labelViewModel, lifecycleOwnerWrapper)

    @JvmStatic
    fun bindWithoutTextPublishers(
        textView: TextView,
        labelViewModel: LabelViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = LabelViewModelBinder.bindWithoutTextPublishers(textView, labelViewModel, lifecycleOwnerWrapper)
}
