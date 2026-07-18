package com.laurentvrevin.androidstarter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laurentvrevin.androidstarter.data.local.dao.UserDao
import com.laurentvrevin.androidstarter.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
