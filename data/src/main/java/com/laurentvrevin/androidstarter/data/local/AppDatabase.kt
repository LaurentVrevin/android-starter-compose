package com.laurentvrevin.androidstarter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laurentvrevin.androidstarter.data.local.dao.SampleDao
import com.laurentvrevin.androidstarter.data.local.entities.SampleEntity

@Database(
    entities = [SampleEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}
