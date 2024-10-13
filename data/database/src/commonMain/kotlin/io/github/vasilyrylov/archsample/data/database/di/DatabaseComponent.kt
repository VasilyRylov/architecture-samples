package io.github.vasilyrylov.archsample.data.database.di

import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase

interface IDatabaseSource {
    val database: ArchSampleDatabase
}

interface IDatabaseComponent {
    val database: ArchSampleDatabase
}

object DatabaseComponent : IDatabaseComponent {

    private var _source: IDatabaseSource? = null
    private inline val source: IDatabaseSource get() = _source
        ?: error("DatabaseComponent must be init before usage")

    fun IDatabaseSource.deploy() {
        _source = this
    }

    override val database: ArchSampleDatabase get() = source.database
}
