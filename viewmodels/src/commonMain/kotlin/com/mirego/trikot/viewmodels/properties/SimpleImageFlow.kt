package com.mirego.trikot.viewmodels.properties

import com.mirego.trikot.viewmodels.NDImageFlow
import com.mirego.trikot.viewmodels.resource.NDImageResource
import org.reactivestreams.Publisher

open class SimpleImageFlow(
    override val imageResource: NDImageResource? = null,
    override val placeholderImageResource: NDImageResource? = null,
    override val tintColor: Color? = null,
    override val accessibilityText: String? = null,
    override val url: String? = null,
    override val onSuccess: Publisher<NDImageFlow>? = null,
    override val onError: Publisher<NDImageFlow>? = null
) : NDImageFlow
