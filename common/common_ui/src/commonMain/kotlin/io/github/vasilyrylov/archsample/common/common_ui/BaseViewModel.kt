package io.github.vasilyrylov.archsample.common.common_ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel {
    private val mainContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate

    protected val coroutineScope = CoroutineScope(mainContext)

    fun onDestroy() {
        coroutineScope.cancel()
        onCleared()
    }

    abstract fun onCleared()
}
