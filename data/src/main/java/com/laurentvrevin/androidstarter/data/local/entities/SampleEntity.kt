package com.laurentvrevin.androidstarter.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.laurentvrevin.androidstarter.core.model.SampleItem

@Entity(tableName = "samples")
data class SampleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String
)

fun SampleEntity.toExternalModel() = SampleItem(
    id = id,
    title = title,
    description = description
)

fun SampleItem.toEntity() = SampleEntity(
    id = id,
    title = title,
    description = description
)
