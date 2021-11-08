package com.mirego.trikot.viewmodels.mutable

import com.mirego.trikot.streams.reactive.Publishers
import com.mirego.trikot.streams.reactive.just
import com.mirego.trikot.viewmodels.*
import com.mirego.trikot.viewmodels.properties.Color
import com.mirego.trikot.viewmodels.properties.ImageState
import com.mirego.trikot.viewmodels.properties.SimpleImageFlow
import com.mirego.trikot.viewmodels.resource.NDImageResource
import org.reactivestreams.Publisher

/**
 * Block to execute to retrieve an imageFlow Publisher for a specific image size
 */
typealias ImageFlowProvider = (width: ImageWidth, height: ImageHeight) -> Publisher<NDImageFlow>

open class NDMutableImageViewModel(var imageFlowProvider: ImageFlowProvider) : NDMutableViewModel(), NDImageViewModel {
    override fun imageFlow(width: ImageWidth, height: ImageHeight): Publisher<NDImageFlow> {
        return imageFlowProvider(width, height)
    }

    override val imageState = Publishers.behaviorSubject(ImageState.NONE)

    override fun setImageState(imageState: ImageState) {
        this.imageState.value = imageState
    }
}

fun simpleImageFlowProvider(url: String? = null, placeholderImageResource: NDImageResource? = null, imageResource: NDImageResource? = null, tintColor: Color? = null): ImageFlowProvider {
    return { _, _ -> SimpleImageFlow(url = url, placeholderImageResource = placeholderImageResource, imageResource = imageResource, tintColor = tintColor).just() }
}
