package io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun TodoListScreenContainer(viewModel: ToDoListViewModel) {

    val viewState by viewModel.viewState.collectAsState()

    TodoListScreen(
        viewState = viewState,
        onAddClick = viewModel::onAddClick,
        onCompletedChange = viewModel::onCompletedChange,
        onToDoClick = viewModel::onToDoClick,
        onConfirmAddClick = viewModel::onConfirmAdd,
        onCancelAddClick = viewModel::onCancelAdd,
    )
}
