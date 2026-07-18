package com.laurentvrevin.androidstarter.core.network

import com.laurentvrevin.androidstarter.core.base.BaseRepository
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@Serializable
data class MockDto(val id: Int, val name: String)

class NetworkTest : BaseRepository() {

    private fun createMockClient(handler: MockRequestHandler): HttpClient {
        return HttpClient(MockEngine) {
            expectSuccess = true
            engine {
                addHandler(handler)
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    @Test
    fun `safeCall returns Success when API call is successful`() = runTest {
        val client = createMockClient { request ->
            respond(
                content = """{"id": 1, "name": "Test"}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val result = safeCall { client.get("test").body<MockDto>() }

        assertTrue(result is NetworkResult.Success)
        assertEquals(1, (result as NetworkResult.Success).data.id)
        assertEquals("Test", result.data.name)
    }

    @Test
    fun `safeCall returns Unauthorized error on 401 response`() = runTest {
        val client = createMockClient { respond("", HttpStatusCode.Unauthorized) }

        val result = safeCall { client.get("test").body<MockDto>() }

        assertTrue(result is NetworkResult.Error)
        assertEquals(NetworkError.UNAUTHORIZED, (result as NetworkResult.Error).error)
    }

    @Test
    fun `safeCall returns NotFound error on 404 response`() = runTest {
        val client = createMockClient { respond("", HttpStatusCode.NotFound) }

        val result = safeCall { client.get("test").body<MockDto>() }

        assertTrue(result is NetworkResult.Error)
        assertEquals(NetworkError.NOT_FOUND, (result as NetworkResult.Error).error)
    }

    @Test
    fun `safeCall returns ServerError on 500 response`() = runTest {
        val client = createMockClient { respond("", HttpStatusCode.InternalServerError) }

        val result = safeCall { client.get("test").body<MockDto>() }

        assertTrue(result is NetworkResult.Error)
        assertEquals(NetworkError.SERVER_ERROR, (result as NetworkResult.Error).error)
    }
}
