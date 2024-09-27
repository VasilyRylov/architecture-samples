package io.github.vasilyrylov.archsample.common.ui.navigation

import kotlin.reflect.KProperty

class RouterHolder<T>(private var routerInstance: T? = null) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return routerInstance ?: error("Router is not initialized")
    }

    fun updateInstance(newRouterInstance: T) {
        routerInstance = newRouterInstance
    }
}
