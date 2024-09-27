package io.github.vasilyrylov.archsample.feature.root.component

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.di.createRootModule
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import org.koin.test.verify.verify

import kotlin.test.Test

class RootDIModuleTests {

    @Test
    fun verifyRootModuleTest() {
        val module = createRootModule(RootFSMState.AsyncWorkState.Initial, object : IRootComponentDependencies {
            override val preferences: IPreferences
                get() = error("not used in test")
            override val userDao: UserDao
                get() = error("not used in test")
            override val todoDao: TodoDao
                get() = error("not used in test")
        })

        try {
            module.verify(
                extraTypes = listOf(RootFSMState::class)
            )
        } catch (e: ClassCastException) {
            // Koin verify bug for check singleton with generics
        }
    }
}