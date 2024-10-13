package io.github.vasilyrylov.archsample.feature.todo.component.impl.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferencesComponent
import io.github.vasilyrylov.archsample.common.data.preferences.PreferencesComponent
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.data.database.di.DatabaseComponent
import io.github.vasilyrylov.archsample.data.database.di.IDatabaseComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.TodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.TodoDetailsComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.TodoListComponent
import io.github.vasilyrylov.archsample.todo.data.api.ITodoRepository
import io.github.vasilyrylov.archsample.todo.data.impl.repository.TodoRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.AuthorizedUserRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoFlowScope
@Component
internal abstract class TodoFlowDIComponent(
    @Component val dependencies: TodoFlowComponent.Dependencies,
    @Component val database: IDatabaseComponent = DatabaseComponent,
    @Component val preferences: IPreferencesComponent = PreferencesComponent,
) : InstanceKeeper.Instance {

    abstract val router: TodoFlowRouter

    @Provides
    fun bind(): ITodoFlowRouter = router

    @Provides
    fun parent(): TodoFlowDIComponent = this

    @Provides
    fun TodoListComponent.DI.bind(): TodoListComponent.Factory = factory

    @Provides
    fun TodoDetailsComponent.DI.bind(): TodoDetailsComponent.Factory = factory

    val AuthorizedUserRepository.bind: IAuthorizedUserRepository
        @Provides get() = this

    val TodoRepository.bind: ITodoRepository
        @Provides get() = this

    @Provides
    fun ArchSampleDatabase.userDao(): UserDao = getUserDao()

    @Provides
    fun ArchSampleDatabase.todoDao(): TodoDao = getTodoDao()
}
