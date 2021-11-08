package com.mirego.trikot.viewmodels.resource

import com.mirego.trikot.foundation.concurrent.freeze

interface NDImageResource {
    companion object {
        val None = freeze(NDNoImageResource() as NDImageResource)
    }
}

class NDNoImageResource : NDImageResource
