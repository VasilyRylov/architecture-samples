package io.github.vasilyrylov.archsample.data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

fun getRoomDatabase(): ArchSampleDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "arch_sample_room.db")
    return Room.databaseBuilder<ArchSampleDatabase>(name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()
}