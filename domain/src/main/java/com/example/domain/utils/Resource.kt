package com.example.domain.utils

sealed class Resource<T>(
    val data : T? = null,
    val message : String? = null
) {
    class Unspecified<T> : Resource<T>()
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data = data)
    class Failure<T>(message: String?): Resource<T>(message = message)
}