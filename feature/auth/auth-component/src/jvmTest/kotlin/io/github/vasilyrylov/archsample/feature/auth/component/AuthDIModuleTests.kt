package io.github.vasilyrylov.archsample.feature.auth.component

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.component.di.createAuthModule
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import org.koin.test.verify.verify

import kotlin.test.Test

class AuthDIModuleTests {
    @Test
    fun verifyAuthModuleTest() {
        val module = createAuthModule(AuthFSMState.Login("", ""), object : IAuthComponentDependencies {
            override val authCompletionUseCase: IAuthCompletionUseCase
                get() = error("not used in test")
            override val authorizedUserRepository: IAuthorizedUserRepository
                get() = error("not used in test")
            override val userDao: UserDao
                get() = error("not used in test")
        })

        module.verify(
            extraTypes = listOf(AuthFSMState::class)
        )
    }
}