package com.trikot.viewModels.sample.resource

import android.content.Context
import com.mirego.trikot.viewModels.resource.ImageResource
import com.mirego.trikot.viewModels.resources.ImageViewModelResourceProvider
import com.trikot.viewModels.sample.R

class SampleImageResourceProvider : ImageViewModelResourceProvider {
    override fun resourceIdFromResource(resource: ImageResource, context: Context): Int? {
        if (resource == ImageResources.ICON) {
            return R.drawable.icon
        }
        return null
    }
}
