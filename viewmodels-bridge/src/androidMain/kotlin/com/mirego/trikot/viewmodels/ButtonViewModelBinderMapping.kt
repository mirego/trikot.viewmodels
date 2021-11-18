package com.mirego.trikot.viewmodels

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object ButtonViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        textView: TextView,
        buttonViewModel: ButtonViewModel?
    ) = ButtonViewModelBinder.bind(textView, buttonViewModel)

    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        view: View,
        buttonViewModel: ButtonViewModel?
    ) = ButtonViewModelBinder.bind(view, buttonViewModel)

    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        imageView: ImageView,
        buttonViewModel: ButtonViewModel?
    ) = ButtonViewModelBinder.bind(imageView, buttonViewModel)

    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        button: Button,
        buttonViewModel: ButtonViewModel?
    ) = ButtonViewModelBinder.bind(button, buttonViewModel)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        textView: TextView,
        buttonViewModel: ButtonViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = ButtonViewModelBinder.bind(textView, buttonViewModel, lifecycleOwnerWrapper)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        view: View,
        buttonViewModel: ButtonViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = ButtonViewModelBinder.bind(view, buttonViewModel, lifecycleOwnerWrapper)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        imageView: ImageView,
        buttonViewModel: ButtonViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = ButtonViewModelBinder.bind(imageView, buttonViewModel, lifecycleOwnerWrapper)

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        button: Button,
        buttonViewModel: ButtonViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) = ButtonViewModelBinder.bind(button, buttonViewModel, lifecycleOwnerWrapper)
}
