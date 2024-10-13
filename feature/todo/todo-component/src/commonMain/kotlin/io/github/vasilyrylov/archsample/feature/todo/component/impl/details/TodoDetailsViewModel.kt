package io.github.vasilyrylov.archsample.feature.todo.component.impl.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.DeleteTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.GetTodoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.component.impl.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoDetailsScope
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsScreen
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsViewState
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@TodoDetailsScope
@Inject
internal class TodoDetailsViewModel(
    private val router: ITodoFlowRouter,
    private val itemId: TodoItemId,
    private val initialState: TodoDetailsViewState,
    private val saveTodo: SaveTodoUseCase,
    private val deleteTodo: DeleteTodoUseCase,
    private val getTodoDetails: GetTodoDetailsUseCase,
    private val completedChange: TodoCompletedChangeUseCase,
) : BaseStateViewModel<TodoDetailsViewState>() {

    @Composable
    fun Ui() {
        val viewState by viewState.collectAsState()
        TodoDetailsScreen(
            viewState = viewState,
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

    private fun updateItemDetails() {
        coroutineScope.launch {
            val itemDetails = getTodoDetails(itemId)
            setState { copy(item = itemDetails) }
        }
    }

    private fun onBackClick() {
        router.back()
    }

    private fun onEditClick() {
        setState { copy(dialog = TodoDetailsScreenDialog.EditTodo) }
    }

    private fun onDeleteClick() {
        coroutineScope.launch {
            deleteTodo(id = currentState.item.id)
            router.back()
        }
    }

    private fun onCompletedChange() {
        coroutineScope.launch {
            completedChange(itemId)
        }
        updateItemDetails()
    }

    private fun onConfirmEdit(updatedItem: TodoItem) {
        coroutineScope.launch {
            saveTodo(updatedItem)
            updateItemDetails()
            setState { copy(dialog = TodoDetailsScreenDialog.None) }
        }
    }

    private fun onCancelEdit() {
        setState { copy(dialog = TodoDetailsScreenDialog.None) }
    }

    override fun createInitialState() = initialState
}
