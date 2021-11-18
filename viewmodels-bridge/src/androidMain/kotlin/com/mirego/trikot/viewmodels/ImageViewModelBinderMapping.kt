package com.mirego.trikot.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Transformation

object ImageViewModelBinderMapping {
    @JvmStatic
    @BindingAdapter("view_model", "placeholderScaleType")
    fun bindOptional(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        placeholderScaleType: ImageView.ScaleType?
    ) = ImageViewModelBinder.bindOptional(
        imageView,
        imageViewModel,
        placeholderScaleType
    )

    @JvmStatic
    @BindingAdapter("view_model", "transformation")
    fun bindOptional(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        transformation: Transformation?
    ) = ImageViewModelBinder.bindOptional(
        imageView,
        imageViewModel,
        transformation
    )

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bindOptional(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper?
    ) = ImageViewModelBinder.bindOptional(
        imageView,
        imageViewModel,
        lifecycleOwnerWrapper
    )

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper", "transformation")
    fun bindOptional(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper?,
        transformation: Transformation?
    ) = ImageViewModelBinder.bindOptional(
        imageView,
        imageViewModel,
        lifecycleOwnerWrapper,
        transformation
    )

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper", "placeholderScaleType")
    fun bindOptional(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper?,
        placeholderScaleType: ImageView.ScaleType?
    ) = ImageViewModelBinder.bindOptional(
        imageView,
        imageViewModel,
        lifecycleOwnerWrapper,
        placeholderScaleType
    )

    @JvmStatic
    @BindingAdapter("view_model", "transformation", "placeholderScaleType")
    fun bindOptional(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        transformation: Transformation?,
        placeholderScaleType: ImageView.ScaleType?
    ) = ImageViewModelBinder.bindOptional(
        imageView,
        imageViewModel,
        transformation,
        placeholderScaleType
    )

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper", "transformation", "placeholderScaleType")
    fun bind(
        imageView: ImageView,
        imageViewModel: ImageViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper?,
        transformation: Transformation?,
        placeholderScaleType: ImageView.ScaleType?
    ) = ImageViewModelBinder.bind(
        imageView,
        imageViewModel,
        lifecycleOwnerWrapper,
        transformation,
        placeholderScaleType
    )
}
