package com.laurentvrevin.androidstarter.core.model

import kotlinx.serialization.Serializable

/**
 * Modèle de données d'exemple.
 * Placé dans :core car partagé entre :data et les features.
 */
@Serializable
data class SampleItem(
    val id: Int,
    val title: String,
    val description: String
)
