package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class AuthComponentDependencies(
    private val _authCompletionUseCase: Lazy<IAuthCompletionUseCase>,
    private val _authorizedUserRepository: Lazy<IAuthorizedUserRepository>,
    private val _userDao: Lazy<UserDao>,
) : IAuthComponentDependencies {
    override val authCompletionUseCase: IAuthCompletionUseCase
        get() = _authCompletionUseCase.value
    override val authorizedUserRepository: IAuthorizedUserRepository
        get() = _authorizedUserRepository.value
    override val userDao: UserDao
        get() = _userDao.value
}