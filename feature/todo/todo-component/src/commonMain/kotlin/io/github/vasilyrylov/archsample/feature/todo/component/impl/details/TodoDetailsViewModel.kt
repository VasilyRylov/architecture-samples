package io.github.vasilyrylov.archsample.feature.todo.component.impl.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.component.viewmodel.BaseStateViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.di.TodoDetailsScope
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.DeleteTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.GetTodoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoDetailsViewState
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import me.tatarka.inject.annotations.Inject

@TodoDetailsScope
@Inject
internal class TodoDetailsViewModel(
    initialState: TodoDetailsViewState,
    private val itemId: TodoItemId,
    private val router: ITodoFlowRouter,
    private val saveTodo: SaveTodoUseCase,
    private val deleteTodo: DeleteTodoUseCase,
    private val getTodoDetails: GetTodoDetailsUseCase,
    private val completedChange: TodoCompletedChangeUseCase,
) : BaseStateViewModel<TodoDetailsViewState>(initialState) {

    @Composable
    fun Ui() {
        val viewState by viewState.collectAsState()
        viewState.Ui(
            onBackClick = ::onBackClick,
            onDeleteClick = ::onDeleteClick,
            onEditClick = ::onEditClick,
            onConfirmEdit = ::onConfirmEdit,
            onCancelEdit = ::onCancelEdit
        )
    }

    init {
        updateItemDetails()
    }

    private fun updateItemDetails() = launch {
        val itemDetails = getTodoDetails(itemId)
        setState { copy(item = itemDetails) }
    }

    private fun onBackClick() {
        router.back()
    }

    private fun onEditClick() {
        setState { copy(dialog = TodoDetailsScreenDialog.EditTodo) }
    }

    private fun onDeleteClick() = launch {
        deleteTodo(id = currentState.item.id)
        router.back()
    }

    private fun onCompletedChange() = launch {
        completedChange(itemId)
        updateItemDetails()
    }

    private fun onConfirmEdit(updatedItem: TodoItem) = launch {
        saveTodo(updatedItem)
        updateItemDetails()
        setState { copy(dialog = TodoDetailsScreenDialog.None) }
    }

    private fun onCancelEdit() {
        setState { copy(dialog = TodoDetailsScreenDialog.None) }
    }
}
