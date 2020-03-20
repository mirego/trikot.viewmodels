package com.mirego.trikot.viewModels.properties

import com.mirego.trikot.viewModels.ImageFlow
import com.mirego.trikot.viewModels.resource.ImageResource
import org.reactivestreams.Publisher

open class SimpleImageFlow(
    override val imageResource: ImageResource? = null,
    override val placeholderImageResource: ImageResource? = null,
    override val tintColor: Color? = null,
    override val accessibilityText: String? = null,
    override val url: String? = null,
    override val onSuccess: Publisher<ImageFlow>? = null,
    override val onError: Publisher<ImageFlow>? = null
) : ImageFlow
