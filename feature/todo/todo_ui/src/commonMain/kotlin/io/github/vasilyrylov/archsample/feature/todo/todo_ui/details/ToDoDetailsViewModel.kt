package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import io.github.vasilyrylov.archsample.common.common_ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.common_ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.DeleteToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.GetToDoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ToDoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.model.ToDoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.model.ToDoDetailsScreenViewState
import kotlinx.coroutines.launch

class ToDoDetailsViewModel(
    private val itemId: ToDoItemId,
    private val routerHolder: RouterHolder<IToDoFlowRouter>,
    private val getToDoDetails: GetToDoDetailsUseCase,
    private val saveToDo: SaveToDoUseCase,
    private val deleteToDo: DeleteToDoUseCase,
    private val completedChange: ToDoCompletedChangeUseCase
) : BaseStateViewModel<ToDoDetailsScreenViewState>() {
    private val router: IToDoFlowRouter
        get() = routerHolder.router!!

    init {
        updateItemDetails()
    }

    private fun updateItemDetails() {
        coroutineScope.launch {
            val itemDetails = getToDoDetails(itemId)
            setState {
                currentState.copy(item = itemDetails)
            }
        }
    }

    fun onBackClick() {
        router.back()
    }

    fun onEditClick() {
        setState {
            currentState.copy(dialog = ToDoDetailsScreenDialog.EditToDo)
        }
    }

    fun onDeleteClick() {
        coroutineScope.launch {
            deleteToDo(currentState.item.id)
            router.back()
        }
    }

    fun onCompletedChange() {
        coroutineScope.launch {
            completedChange(itemId)
        }
        updateItemDetails()
    }

    fun onConfirmEdit(updatedItem: ToDoItem) {
        coroutineScope.launch {
            saveToDo(updatedItem)
            updateItemDetails()
            setState {
                currentState.copy(dialog = ToDoDetailsScreenDialog.None)
            }
        }
    }

    fun onCancelEdit() {
        setState {
            currentState.copy(dialog = ToDoDetailsScreenDialog.None)
        }
    }

    override fun createInitialState() = ToDoDetailsScreenViewState(
        item = ToDoItem("", false)
    )
}