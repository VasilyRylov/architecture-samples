package io.github.vasilyrylov.archsample.feature.todo.component.details.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoDetailsScope
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsViewModel
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsViewState
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoDetailsScope
@Component
abstract class TodoDetailsDIComponent(
    @Component val parent: TodoFlowDIComponent,
    private val initialState: TodoDetailsViewState,
    private val itemId: TodoItemId,
) : InstanceKeeper.Instance {

    abstract val viewModel: TodoDetailsViewModel

    @Provides
    protected fun getInitialState(): TodoDetailsViewState = initialState

    @Provides
    protected fun getItemId(): TodoItemId = itemId

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
