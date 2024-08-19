package io.github.vasilyrylov.archsample.common.common_ui.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseStateViewModel<State> : BaseViewModel() {

    private val mutableViewState: MutableStateFlow<State> by lazy { MutableStateFlow(createInitialState()) }
    val viewState by lazy { mutableViewState.asStateFlow() }


    protected abstract fun createInitialState(): State

    val currentState: State
        get() = viewState.value

    fun setState(reduce: State.() -> State) = mutableViewState.update(reduce)
}
