package com.mirego.trikot.viewmodels.properties

import com.mirego.trikot.foundation.concurrent.freeze
import kotlin.js.JsExport
import kotlin.js.JsName

typealias ViewModelActionBlock = (actionContext: Any?) -> Unit

@JsExport
open class ViewModelAction(private var action: ViewModelActionBlock) {
    fun execute() {
        execute(null)
    }

    @JsName("executeWithActionContext")
    open fun execute(actionContext: Any? = null) {
        action(actionContext)
    }

    companion object {
        val None = freeze(ViewModelAction {})
    }
}
