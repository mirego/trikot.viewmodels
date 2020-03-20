package com.mirego.trikot.viewModels.mutable

import com.mirego.trikot.viewModels.ImageFlow
import com.mirego.trikot.viewModels.ImageHeight
import com.mirego.trikot.viewModels.ImageWidth
import com.mirego.trikot.viewModels.ImageViewModel
import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.ImageState
import com.mirego.trikot.viewModels.properties.SimpleImageFlow
import com.mirego.trikot.viewModels.resource.ImageResource
import com.mirego.trikot.streams.reactive.Publishers
import org.reactivestreams.Publisher

/**
 * Block to execute to retrieve an imageFlow Publisher for a specific image size
 */
typealias ImageFlowProvider = (width: ImageWidth, height: ImageHeight) -> Publisher<ImageFlow>

open class MutableImageViewModel(var imageFlowProvider: ImageFlowProvider) : MutableViewModel(), ImageViewModel {
    override fun imageFlow(width: ImageWidth, height: ImageHeight): Publisher<ImageFlow> {
        return imageFlowProvider(width, height)
    }

    override val imageState = Publishers.behaviorSubject(ImageState.NONE)

    override fun setImageState(imageState: ImageState) {
        this.imageState.value = imageState
    }
}

fun simpleImageFlowProvider(url: String? = null, placeholderImageResource: ImageResource? = null, imageResource: ImageResource? = null, tintColor: Color? = null): ImageFlowProvider {
    return { _, _ -> Publishers.behaviorSubject(SimpleImageFlow(url = url, placeholderImageResource = placeholderImageResource, imageResource = imageResource, tintColor = tintColor)) }
}
