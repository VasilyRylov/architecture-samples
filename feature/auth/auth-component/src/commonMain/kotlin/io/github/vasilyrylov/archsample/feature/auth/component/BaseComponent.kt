package io.github.vasilyrylov.archsample.feature.auth.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import io.github.vasilyrylov.archsample.feature.auth.ui.ViewStateMapper
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

interface CoroutineComponentContext : ComponentContext {

    val coroutineScope: CoroutineScope

    fun launch(
        // errorHandler и прочее, что нужно для запуска корутины
        block: suspend CoroutineScope.() -> Unit,
    )

    fun <T> Flow<T>.stateIn(initialValue: T): StateFlow<T>
}

class BaseComponent private constructor(
    context: ComponentContext
) : CoroutineComponentContext, ComponentContext by context {

    private val mainContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate

    override val coroutineScope = CoroutineScope(mainContext)

    override fun launch(
        // errorHandler
        block: suspend CoroutineScope.() -> Unit,
    ) {
        coroutineScope.launch(block = block)
    }

    override fun <T> Flow<T>.stateIn(initialValue: T): StateFlow<T> = stateIn(
        scope = coroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = initialValue
    )

    init {
        lifecycle.subscribe(
            onDestroy = { coroutineScope.cancel() }
        )
    }

    companion object {
        fun ComponentContext.withCoroutine() = BaseComponent(this)
    }
}
