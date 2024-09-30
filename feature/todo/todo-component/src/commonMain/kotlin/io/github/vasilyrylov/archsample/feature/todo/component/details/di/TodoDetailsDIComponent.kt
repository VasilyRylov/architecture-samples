package io.github.vasilyrylov.archsample.feature.todo.component.details.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoDetailsScope
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoDetailsScope
@Component
abstract class TodoDetailsDIComponent(
    private val itemId: TodoItemId,
    @Component val parent: TodoFlowDIComponent
) : InstanceKeeper.Instance {

    abstract val viewModel: TodoDetailsViewModel

    @Provides
    protected fun getItemId(): TodoItemId = itemId
}
