package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.callback

import io.github.vasilyrylov.archsample.feature.root.domain.usecase.LogoutUseCase
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import me.tatarka.inject.annotations.Inject

@Inject
class TodoListCallbackImpl(
    lazyLogoutUseCase: Lazy<LogoutUseCase>,
) : TodoListCallback {

    private val lazyLogoutUseCase by lazyLogoutUseCase

    override fun onLogout() = lazyLogoutUseCase()
}
