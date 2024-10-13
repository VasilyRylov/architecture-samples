package io.github.vasilyrylov.archsample.common.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel {
    private val mainContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate

    protected val coroutineScope = CoroutineScope(mainContext)

    protected fun launch(
        // errorHandler
        block: suspend CoroutineScope.() -> Unit,
    ) {
        coroutineScope.launch(block = block)
    }

    protected fun <T> Flow<T>.stateIn(initialValue: T): StateFlow<T> = stateIn(
        scope = coroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = initialValue
    )

    fun onDestroy() {
        coroutineScope.cancel()
        onCleared()
    }

    open fun onCleared() {}
}
