package com.laurentvrevin.androidstarter.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val lastSyncTimestamp: Long = System.currentTimeMillis()
)
