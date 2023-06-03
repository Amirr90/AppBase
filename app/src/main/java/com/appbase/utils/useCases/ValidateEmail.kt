package com.appbase.utils.useCases

class ValidateEmail {
    fun invoke(email: String) =
        when {
            email.isEmpty() -> {
                Result(
                    success = false,
                    error = "email is empty !!"
                )
            }
            !email.isValidEmail() -> {
                Result(
                    success = false,
                    error = "not a valid email !!"
                )
            }
            else -> Result(
                success = true
            )
        }
}