package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class AuthComponentDependencies(
    private val lazyAuthCompletionUseCase: Lazy<IAuthCompletionUseCase>,
    private val lazyAuthorizedUserRepository: Lazy<IAuthorizedUserRepository>,
    private val lazyUserDao: Lazy<UserDao>,
) : IAuthComponentDependencies {
    override val authCompletionUseCase: IAuthCompletionUseCase
        get() = lazyAuthCompletionUseCase.value
    override val authorizedUserRepository: IAuthorizedUserRepository
        get() = lazyAuthorizedUserRepository.value
    override val userDao: UserDao
        get() = lazyUserDao.value
}