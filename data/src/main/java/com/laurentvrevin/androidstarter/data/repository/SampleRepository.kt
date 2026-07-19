package com.laurentvrevin.androidstarter.data.repository

import com.laurentvrevin.androidstarter.core.model.SampleItem
import com.laurentvrevin.androidstarter.data.base.BaseRepository
import com.laurentvrevin.androidstarter.data.local.dao.SampleDao
import com.laurentvrevin.androidstarter.data.local.entities.toEntity
import com.laurentvrevin.androidstarter.data.local.entities.toExternalModel
import com.laurentvrevin.androidstarter.data.network.NetworkResult
import com.laurentvrevin.androidstarter.data.network.map
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SampleRepository {
    fun getSamples(): Flow<List<SampleItem>>
    suspend fun refreshSamples(): NetworkResult<Unit>
}

class SampleRepositoryImpl(
    private val api: HttpClient,
    private val dao: SampleDao
) : BaseRepository(), SampleRepository {

    override fun getSamples(): Flow<List<SampleItem>> {
        return dao.getAllSamples().map { entities ->
            entities.map { it.toExternalModel() }
        }
    }

    override suspend fun refreshSamples(): NetworkResult<Unit> {
        return safeCall {
            // Simulation d'un appel API (URL fictive)
            // En production, on utiliserait une vraie URL.
            // Ici on simule un retour avec des données statiques pour le starter.
            val items = listOf(
                SampleItem(1, "Modern Architecture", "Multi-module with clean boundaries."),
                SampleItem(2, "Jetpack Compose", "Declarative UI with Material 3."),
                SampleItem(3, "Koin & Ktor", "Lightweight DI and resilient networking.")
            )
            
            // Simulation d'un délai réseau
            kotlinx.coroutines.delay(1000)
            
            // Mise à jour de la base locale (SSOT)
            dao.deleteAllSamples()
            dao.upsert(items.map { it.toEntity() })
        }.map { Unit }
    }
}
