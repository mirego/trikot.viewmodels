package com.mirego.trikot.viewmodels.resource

import com.mirego.trikot.foundation.concurrent.freeze
import kotlin.js.JsExport

@JsExport
interface TextAppearanceResource {
    companion object {
        val None = freeze(NoTextAppearanceResource() as TextAppearanceResource)
    }
}

@JsExport
class NoTextAppearanceResource : TextAppearanceResource
