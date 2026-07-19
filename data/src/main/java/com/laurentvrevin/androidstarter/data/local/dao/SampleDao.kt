package com.laurentvrevin.androidstarter.data.local.dao

import androidx.room.*
import com.laurentvrevin.androidstarter.data.local.entities.SampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao : BaseDao<SampleEntity> {
    
    @Query("SELECT * FROM samples")
    fun getAllSamples(): Flow<List<SampleEntity>>

    @Query("SELECT * FROM samples WHERE id = :id")
    suspend fun getSampleById(id: Int): SampleEntity?

    @Query("DELETE FROM samples")
    suspend fun deleteAllSamples()
}
