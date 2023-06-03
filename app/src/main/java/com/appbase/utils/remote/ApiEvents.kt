package com.appbase.utils.remote

sealed class ApiEvents {
    data class Success<ResponseType>(val data: ResponseType) : ApiEvents()
    data class Failure<ResponseType>(val data: ResponseType? = null, val error: Any) :
        ApiEvents()
}
