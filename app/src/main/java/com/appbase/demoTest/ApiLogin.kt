package com.appbase.demoTest

import com.appbase.utils.remote.ApiEvents
import javax.inject.Inject

class ApiLogin @Inject constructor() : Authentication {
    override suspend fun login(email: String, password: String): ApiEvents {
        return ApiEvents.Success("ApiAuthentication Success")
    }
}