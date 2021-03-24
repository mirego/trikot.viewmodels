package com.mirego.trikot.viewmodels.lifecycle

import com.mirego.trikot.foundation.concurrent.AtomicReference
import com.mirego.trikot.foundation.concurrent.setOrThrow
import com.mirego.trikot.streams.reactive.BehaviorSubjectImpl
import kotlinx.cinterop.ObjCAction
import org.reactivestreams.Publisher
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSThread
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.UIKit.UIApplicationState
import platform.UIKit.UIApplicationWillEnterForegroundNotification
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.sel_registerName
import kotlin.native.concurrent.freeze

@Suppress("unused")
actual class ApplicationStatePublisher :
    BehaviorSubjectImpl<ApplicationState>(),
    Publisher<ApplicationState> {

    private val observer = ApplicationStateObserver()

    init {
        if (NSThread.isMainThread) {
            setInitialValue()
        } else {
            dispatch_async(
                dispatch_get_main_queue(),
                {
                    setInitialValue()
                }.freeze()
            )
        }
    }

    private fun setInitialValue() {
        value = when (UIApplication.sharedApplication.applicationState) {
            UIApplicationState.UIApplicationStateBackground -> ApplicationState.BACKGROUND
            else -> ApplicationState.FOREGROUND
        }
    }

    override fun onFirstSubscription() {
        super.onFirstSubscription()
        observer.start {
            value = it
        }
    }

    override fun onNoSubscription() {
        observer.stop()
        super.onNoSubscription()
    }

    private class ApplicationStateObserver : NSObject() {
        private var callback: AtomicReference<((ApplicationState) -> Unit)?> = AtomicReference(null)

        fun start(closure: (ApplicationState) -> Unit) {
            callback.setOrThrow(closure)
            NSNotificationCenter.defaultCenter.addObserver(
                this,
                sel_registerName("willEnterForeground"),
                UIApplicationWillEnterForegroundNotification,
                null
            )
            NSNotificationCenter.defaultCenter.addObserver(
                this,
                sel_registerName("didEnterBackground"),
                UIApplicationDidEnterBackgroundNotification,
                null
            )
        }

        fun stop() {
            callback.setOrThrow(null)
            NSNotificationCenter.defaultCenter.removeObserver(this)
        }

        @ObjCAction
        @Suppress("unused")
        fun willEnterForeground() {
            callback.value?.let { it(ApplicationState.FOREGROUND) }
        }

        @ObjCAction
        @Suppress("unused")
        fun didEnterBackground() {
            callback.value?.let { it(ApplicationState.BACKGROUND) }
        }
    }
}
