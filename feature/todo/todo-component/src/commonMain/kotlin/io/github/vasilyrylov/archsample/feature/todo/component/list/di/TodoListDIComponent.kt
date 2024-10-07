package io.github.vasilyrylov.archsample.feature.todo.component.list.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoListScope
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListViewModel
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListViewState
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoListScope
@Component
abstract class TodoListDIComponent(
    @Component val parent: TodoFlowDIComponent,
    private val initialState: TodoListViewState,
) : InstanceKeeper.Instance {

    abstract val viewModel: TodoListViewModel

    @Provides
    protected fun getInitialState(): TodoListViewState = initialState

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
