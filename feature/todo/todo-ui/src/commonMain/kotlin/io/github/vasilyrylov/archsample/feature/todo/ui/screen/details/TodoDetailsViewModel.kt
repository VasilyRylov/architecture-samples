package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details

import io.github.vasilyrylov.archsample.common.ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.DeleteTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.GetTodoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.api.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsViewState
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class TodoDetailsViewModel(
    private val itemId: TodoItemId,
    private val getTodoDetails: GetTodoDetailsUseCase,
    private val saveTodo: SaveTodoUseCase,
    private val deleteTodo: DeleteTodoUseCase,
    private val completedChange: TodoCompletedChangeUseCase,
    routerHolder: RouterHolder<ITodoFlowRouter>,
) : BaseStateViewModel<TodoDetailsViewState>() {

    private val router by routerHolder

    override fun createInitialState() = TodoDetailsViewState(
        item = TodoItem(text = "", completed = false)
    )

    init {
        updateItemDetails()
    }

    private fun updateItemDetails() {
        coroutineScope.launch {
            val itemDetails = getTodoDetails(itemId)
            setState { copy(item = itemDetails) }
        }
    }

    fun onBackClick() {
        router.back()
    }

    fun onEditClick() {
        setState { copy(dialog = TodoDetailsScreenDialog.EditTodo) }
    }

    fun onDeleteClick() {
        coroutineScope.launch {
            deleteTodo(id = currentState.item.id)
            router.back()
        }
    }

    fun onCompletedChange() {
        coroutineScope.launch {
            completedChange(itemId)
        }
        updateItemDetails()
    }

    fun onConfirmEdit(updatedItem: TodoItem) {
        coroutineScope.launch {
            saveTodo(updatedItem)
            updateItemDetails()
            setState { copy(dialog = TodoDetailsScreenDialog.None) }
        }
    }

    fun onCancelEdit() {
        setState { copy(dialog = TodoDetailsScreenDialog.None) }
    }
}
