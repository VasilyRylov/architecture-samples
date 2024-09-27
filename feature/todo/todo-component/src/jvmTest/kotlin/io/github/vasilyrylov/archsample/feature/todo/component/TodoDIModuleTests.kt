package io.github.vasilyrylov.archsample.feature.todo.component

import com.benasher44.uuid.uuid4
import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.createTodoDetailsModule
import io.github.vasilyrylov.archsample.feature.todo.component.di.createTodoFlowModule
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.createTodoListModule
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import org.koin.dsl.module
import org.koin.test.verify.verify

import kotlin.test.Test

class TodoDIModuleTests {

    @Test
    fun verifyTodoModuleTest() {
        val flowModule = createTodoFlowModule(object : ITodoComponentDependencies {
            override val authorizedUserRepository: IAuthorizedUserRepository
                get() = error("not used in test")
            override val logoutUseCase: ILogoutUseCase
                get() = error("not used in test")
            override val todoDao: TodoDao
                get() = error("not used in test")
        })
        val listModule = createTodoListModule()
        val detailsModule = createTodoDetailsModule(TodoItemId(uuid4()))

        val moduleSetList = module {
            includes(flowModule, listModule)
        }

        val moduleSetDetails = module {
            includes(flowModule, detailsModule)
        }


        try {
            moduleSetList.verify(
                extraTypes = listOf()
            )
        } catch (e: ClassCastException) {
            // Koin verify bug for check singleton with generics
        }

        try {
            moduleSetDetails.verify(
                extraTypes = listOf()
            )
        } catch (e: ClassCastException) {
            // Koin verify bug for check singleton with generics
        }
    }
}