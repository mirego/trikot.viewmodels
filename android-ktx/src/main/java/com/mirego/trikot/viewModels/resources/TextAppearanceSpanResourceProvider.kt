package com.mirego.trikot.viewmodels.resources

import android.content.Context
import android.text.style.TextAppearanceSpan
import com.mirego.trikot.viewmodels.resource.TextAppearanceResource

interface TextAppearanceSpanResourceProvider {
    fun resourceIdFromResource(
        resource: TextAppearanceResource,
        context: Context
    ): TextAppearanceSpan?
}

object TextAppearanceSpanResourceManager {
    var provider: TextAppearanceSpanResourceProvider =
        DefaultTextAppearanceSpanResourceProvider()
}

class DefaultTextAppearanceSpanResourceProvider : TextAppearanceSpanResourceProvider {
    override fun resourceIdFromResource(
        resource: TextAppearanceResource,
        context: Context
    ): TextAppearanceSpan? {
        return null
    }
}
