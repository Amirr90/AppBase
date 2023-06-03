package com.appbase.demoTest

import com.appbase.utils.remote.ApiEvents

interface Authentication {
    suspend fun login(email: String, password: String): ApiEvents
}