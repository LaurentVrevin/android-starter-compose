package com.laurentvrevin.androidstarter.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.laurentvrevin.androidstarter.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}
