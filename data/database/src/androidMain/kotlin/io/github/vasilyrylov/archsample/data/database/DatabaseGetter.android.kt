package io.github.vasilyrylov.archsample.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getRoomDatabase(appContext: Context): ArchSampleDatabase {
    val dbFile = appContext.getDatabasePath("arch_sample_room.db")
    return Room.databaseBuilder<ArchSampleDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}