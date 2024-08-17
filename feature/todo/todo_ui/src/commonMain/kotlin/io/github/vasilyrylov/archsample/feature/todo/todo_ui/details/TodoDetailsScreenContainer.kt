package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun TodoDetailsScreenContainer(
    viewModel: ToDoDetailsViewModel
) {

    val viewState by viewModel.viewState.collectAsState()

    TodoDetailsScreen(
        viewState = viewState,
        onBackClick = viewModel::onBackClick,
        onDeleteClick = viewModel::onDeleteClick,
        onEditClick = viewModel::onEditClick,
        onConfirmEdit = viewModel::onConfirmEdit,
        onCancelEdit = viewModel::onCancelEdit
    )
}
