package io.github.vasilyrylov.archsample.todo.data.api.di

import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.di.DatabaseDI
import io.github.vasilyrylov.archsample.todo.data.api.ITodoRepository
import io.github.vasilyrylov.archsample.todo.data.impl.repository.TodoRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

interface TodoDataDI {
    val todoRepository: ITodoRepository

    companion object {
        val Instance: TodoDataDI get() = TodoDataDIComponent::class.create()
    }
}

@Component
internal abstract class TodoDataDIComponent(
    @Component val database: DatabaseDI = DatabaseDI.Instance,
) : TodoDataDI {

    val TodoRepository.bind: ITodoRepository
        @Provides get() = this

    val ArchSampleDatabase.bind: TodoDao
        @Provides get() = getTodoDao()
}
