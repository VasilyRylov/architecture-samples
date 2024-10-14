package io.github.vasilyrylov.archsample.common.component.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseStateViewModel<State>(initialState: State) : BaseViewModel() {

    private val mutableViewState: MutableStateFlow<State> by lazy { MutableStateFlow(initialState) }
    val viewState by lazy { mutableViewState.asStateFlow() }

    val currentState: State
        get() = viewState.value

    fun setState(reduce: State.() -> State) = mutableViewState.update(reduce)
}
