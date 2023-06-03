package com.appbase.demoTest

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface LoginApi {
    @POST("login")
    suspend fun login(
        @QueryMap map: HashMap<String, Any>,
    ): Response<String>
}