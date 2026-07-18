package com.laurentvrevin.androidstarter.core.base

import com.laurentvrevin.androidstarter.core.network.NetworkError
import com.laurentvrevin.androidstarter.core.network.NetworkResult
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.SerializationException

abstract class BaseRepository {

    /**
     * Exécute un appel réseau de manière sécurisée en interceptant les exceptions Ktor.
     * 
     * Cette méthode transforme les erreurs techniques (401, 404, 500, Timeout, etc.)
     * en une [NetworkError] métier simplifiée et facile à traiter par l'UI.
     */
    suspend fun <T> safeCall(call: suspend () -> T): NetworkResult<T> {
        return try {
            NetworkResult.Success(call())
        } catch (e: RedirectResponseException) { // 3xx
            NetworkResult.Error(NetworkError.UNKNOWN)
        } catch (e: ClientRequestException) { // 4xx
            when (e.response.status.value) {
                401 -> NetworkResult.Error(NetworkError.UNAUTHORIZED)
                403 -> NetworkResult.Error(NetworkError.FORBIDDEN)
                404 -> NetworkResult.Error(NetworkError.NOT_FOUND)
                408 -> NetworkResult.Error(NetworkError.REQUEST_TIMEOUT)
                413 -> NetworkResult.Error(NetworkError.PAYLOAD_TOO_LARGE)
                else -> NetworkResult.Error(NetworkError.UNKNOWN)
            }
        } catch (e: ServerResponseException) { // 5xx
            NetworkResult.Error(NetworkError.SERVER_ERROR)
        } catch (e: IOException) { // Network issues
            NetworkResult.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            NetworkResult.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            NetworkResult.Error(NetworkError.UNKNOWN)
        }
    }
}
