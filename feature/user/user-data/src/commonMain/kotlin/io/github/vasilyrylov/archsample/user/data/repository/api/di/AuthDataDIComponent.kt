package io.github.vasilyrylov.archsample.user.data.repository.api.di

import io.github.vasilyrylov.archsample.common.data.preferences.PreferencesDI
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.data.database.di.DatabaseDI
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.IUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.AuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.UserRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

interface AuthDataDI {
    val userRepository: IUserRepository
    val authorizedUserRepository: IAuthorizedUserRepository

    companion object {
        val Instance: AuthDataDI get() = AuthDataDIComponent::class.create()
    }
}

@Component
internal abstract class AuthDataDIComponent(
    @Component val database: DatabaseDI = DatabaseDI.Instance,
    @Component val preferences: PreferencesDI = PreferencesDI.Instance,
) : AuthDataDI {

    val AuthorizedUserRepository.bind: IAuthorizedUserRepository
        @Provides get() = this

    val UserRepository.bind: IUserRepository
        @Provides get() = this

    val ArchSampleDatabase.bind: UserDao
        @Provides get() = getUserDao()
}
