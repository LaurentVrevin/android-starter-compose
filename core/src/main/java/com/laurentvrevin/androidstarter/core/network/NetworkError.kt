package com.laurentvrevin.androidstarter.core.network

enum class NetworkError {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    NO_INTERNET,
    UNKNOWN
}
