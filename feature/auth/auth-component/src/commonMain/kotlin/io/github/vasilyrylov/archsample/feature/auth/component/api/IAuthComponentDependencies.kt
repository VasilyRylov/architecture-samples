package io.github.vasilyrylov.archsample.feature.auth.component.api

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase

interface IAuthComponentDependencies {
    // Как-то стоит байндить эти зависимости внутри модуля с реализацией, а не тягать вручную через интерфейс.
    // В Hilt точно так можно, и с этим di тоже должно как-то получиться.
    // Возможно сделать какие-то классы-модули биндинга в модуле с реализацией, а в рут просто собрать их нужные скоупы.
    val authCompletionUseCase: IAuthCompletionUseCase
    val authorizedUserRepository: IAuthorizedUserRepository
    val userDao: UserDao
}
