package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import io.github.vasilyrylov.archsample.common.common_ui.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.DeleteToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.GetToDoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ToDoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
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
        updateState()
    }

    private fun updateState() {
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
        updateState()
    }

    fun onEdited(text: String) {
        val updatedItem = currentState.item.copy(text = text)
        coroutineScope.launch {
            saveToDo(updatedItem)
            updateState()
        }
    }

    override fun createInitialState() = ToDoDetailsScreenViewState(
        item = ToDoItem("", false)
    )
}