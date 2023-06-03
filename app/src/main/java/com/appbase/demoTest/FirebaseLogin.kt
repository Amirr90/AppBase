package com.appbase.demoTest

import com.appbase.utils.remote.ApiEvents
import javax.inject.Inject

class FirebaseLogin @Inject constructor(
    private val api: LoginApi,
) : Authentication {
    override suspend fun login(email: String, password: String): ApiEvents {
        val map = HashMap<String, Any>()
        map["email"] = email
        map["password"] = password
        val response = api.login(map)
        if (response.isSuccessful && response.body() != null) {
            val code = response.code()

            return when {
                code == 200 -> {
                    ApiEvents.Success("FirebaseLogin Success !!")
                }
                code.toString().startsWith("5") -> {
                    ApiEvents.Failure(data = null, error = "Server Error")

                }
                code == 404 -> {
                    ApiEvents.Failure(data = null,
                        error = "something went wrong !!\nerror code $code")

                }

                else -> {
                    ApiEvents.Failure(data = null, error = "something went wrong !!")

                }
            }
        } else return ApiEvents.Failure(data = null,
            error = "something went wrong !! \nerror code => ${response.code()}")

    }
}