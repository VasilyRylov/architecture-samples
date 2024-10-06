package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class AuthComponentDependencies(
    lazyAuthCompletionUseCase: Lazy<IAuthCompletionUseCase>,
    lazyAuthorizedUserRepository: Lazy<IAuthorizedUserRepository>,
    lazyUserDao: Lazy<UserDao>,
) : IAuthComponentDependencies {
    override val authCompletionUseCase by lazyAuthCompletionUseCase
    override val authorizedUserRepository by lazyAuthorizedUserRepository
    override val userDao by lazyUserDao
}
