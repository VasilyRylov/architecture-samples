package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details

import io.github.vasilyrylov.archsample.common.ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.DeleteToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.GetToDoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ToDoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.ToDoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.ToDoDetailsViewState
import kotlinx.coroutines.launch

class ToDoDetailsViewModel(
    private val itemId: ToDoItemId,
    private val routerHolder: RouterHolder<IToDoFlowRouter>,
    private val getToDoDetails: GetToDoDetailsUseCase,
    private val saveToDo: SaveToDoUseCase,
    private val deleteToDo: DeleteToDoUseCase,
    private val completedChange: ToDoCompletedChangeUseCase
) : BaseStateViewModel<ToDoDetailsViewState>() {

    private val router: IToDoFlowRouter
        get() = routerHolder.router

    override fun createInitialState() = ToDoDetailsViewState(
        item = ToDoItem(text = "", completed = false)
    )

    init {
        updateItemDetails()
    }

    private fun updateItemDetails() {
        coroutineScope.launch {
            val itemDetails = getToDoDetails(itemId)
            setState { copy(item = itemDetails) }
        }
    }

    fun onBackClick() {
        router.back()
    }

    fun onEditClick() {
        setState { copy(dialog = ToDoDetailsScreenDialog.EditToDo) }
    }

    fun onDeleteClick() {
        coroutineScope.launch {
            deleteToDo(id = currentState.item.id)
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
            setState { copy(dialog = ToDoDetailsScreenDialog.None) }
        }
    }

    fun onCancelEdit() {
        setState { copy(dialog = ToDoDetailsScreenDialog.None) }
    }
}
