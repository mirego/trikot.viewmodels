package com.trikot.viewModels.sample

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mirego.trikot.viewModels.resources.ImageViewModelResourceManager
import com.trikot.viewModels.sample.resource.SampleImageResourceProvider
import com.trikot.sample.Environment
import kotlinx.serialization.ImplicitReflectionSerializer

class TrikotApplication : Application() {

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            packageName + "_preferences", Context.MODE_PRIVATE
        )
    }

    @ImplicitReflectionSerializer
    override fun onCreate() {
        super.onCreate()
        ImageViewModelResourceManager.provider = SampleImageResourceProvider()
        Environment.flavor = Environment.Flavor.valueOf(BuildConfig.BUILD_TYPE.toUpperCase())
    }
}
