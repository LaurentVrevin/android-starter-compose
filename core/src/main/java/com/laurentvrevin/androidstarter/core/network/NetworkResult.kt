package com.laurentvrevin.androidstarter.core.network

/**
 * Wrapper générique pour sécuriser les réponses réseau.
 * 
 * Force le développeur à gérer explicitement les deux cas possibles :
 * [Success] pour les données valides, [Error] pour tout échec typé.
 */
sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(val error: NetworkError) : NetworkResult<Nothing>
}

inline fun <T, R> NetworkResult<T>.map(transform: (T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data))
        is NetworkResult.Error -> NetworkResult.Error(error)
    }
}

fun <T> NetworkResult<T>.asResult(): Result<T> {
    return when (this) {
        is NetworkResult.Success -> Result.success(data)
        is NetworkResult.Error -> Result.failure(Exception(error.name))
    }
}
