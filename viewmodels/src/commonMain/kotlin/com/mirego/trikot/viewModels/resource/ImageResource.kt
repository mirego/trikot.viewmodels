package com.mirego.trikot.viewmodels.resource

import com.mirego.trikot.foundation.concurrent.freeze
import kotlin.js.JsExport

@JsExport
interface ImageResource {
    companion object {
        val None = freeze(NoImageResource() as ImageResource)
    }
}

@JsExport
class NoImageResource : ImageResource
