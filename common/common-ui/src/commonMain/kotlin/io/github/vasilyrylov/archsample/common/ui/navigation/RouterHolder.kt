package io.github.vasilyrylov.archsample.common.ui.navigation

class RouterHolder<T>(private var routerInstance: T? = null) {
    val router: T
        get() = routerInstance ?: error("Router is not initialized")

    fun updateInstance(newRouterInstance: T) {
        routerInstance = newRouterInstance
    }
}
