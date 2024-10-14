package io.github.vasilyrylov.archsample.feature.todo.component.impl.details.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.TodoDetailsViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoDetailsViewState
import io.github.vasilyrylov.archsample.todo.data.api.di.TodoDataDI
import io.github.vasilyrylov.archsample.user.data.repository.api.di.AuthDataDI
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoDetailsScope
@Component
internal abstract class TodoDetailsDIComponent(
    @get:Provides val initialState: TodoDetailsViewState,
    @get:Provides val itemId: TodoItemId,
    @get:Provides val router: ITodoFlowRouter,
    @Component val todoData: TodoDataDI = TodoDataDI.Instance,
    @Component val authData: AuthDataDI = AuthDataDI.Instance,
) : InstanceKeeper.Instance {

    abstract val viewModel: TodoDetailsViewModel

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
