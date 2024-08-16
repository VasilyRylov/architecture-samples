package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import io.github.vasilyrylov.archsample.common.common_ui.BaseViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.DeleteToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.GetToDoDetailsUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoDetailsViewModel(
    private val toDoItemId: ToDoItemId,
    private val routerHolder: RouterHolder<IToDoFlowRouter>,
    private val getToDoDetails: GetToDoDetailsUseCase,
    private val saveToDo: SaveToDoUseCase,
    private val deleteToDo: DeleteToDoUseCase,
) : BaseViewModel() {
    private val router: IToDoFlowRouter
        get() = routerHolder.router!!

    private val _state = MutableStateFlow(ToDoItem("", false))

    val state = _state.asStateFlow()

    init {
        initState()
    }

    private fun initState() {
        coroutineScope.launch {
            _state.value = getToDoDetails(toDoItemId)
        }
    }

    fun onBackClick() {
        router.back()
    }

    fun onEditClick() {

    }

    fun onDeleteClick() {
        coroutineScope.launch {
            deleteToDo(state.value.id)
            router.back()
        }
    }

    fun onCompletedChange() {
        val currentToDoItem = state.value
        val updatedToDoItem = currentToDoItem.copy(completed = !currentToDoItem.completed)
        updateToDo(updatedToDoItem)
    }

    fun onEdited(text: String) {
        val currentToDoItem = state.value
        val updatedToDoItem = currentToDoItem.copy(text = text)
        updateToDo(updatedToDoItem)
    }

    private fun updateToDo(toDoItem: ToDoItem) {
        _state.value = toDoItem
        coroutineScope.launch {
            saveToDo(toDoItem)
        }
    }
}