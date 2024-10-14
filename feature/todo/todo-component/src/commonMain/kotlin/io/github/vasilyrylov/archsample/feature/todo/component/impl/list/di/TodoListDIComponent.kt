package io.github.vasilyrylov.archsample.feature.todo.component.impl.list.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.TodoListViewModel
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoListViewState
import io.github.vasilyrylov.archsample.todo.data.api.di.TodoDataDI
import io.github.vasilyrylov.archsample.user.data.repository.api.di.AuthDataDI
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoListScope
@Component
internal abstract class TodoListDIComponent(
    @get:Provides val initialState: TodoListViewState,
    @get:Provides val router: ITodoFlowRouter,
    @get:Provides val callback: TodoListCallback,
    @Component val todoData: TodoDataDI = TodoDataDI.Instance,
    @Component val authData: AuthDataDI = AuthDataDI.Instance,
) : InstanceKeeper.Instance {

    abstract val viewModel: TodoListViewModel

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
