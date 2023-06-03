package com.appbase.utils.useCases

class ValidatePassword {
    fun invoke(password: String): Result {
        val isValidPassword = password.any {
            it.isDigit()
        } && password.any {
            it.isLetter()
        } && password.any {
            it.isLowerCase()
        } && password.any {
            it.isUpperCase()
        }
        return when {
            password.isEmpty() -> {
                Result(
                    success = false,
                    error = "password is empty !!"
                )
            }
            !isValidPassword -> {
                Result(
                    success = false,
                    error = "password should contain an upper and lower alphabet and should contain one Digit"
                )
            }
            else -> Result(
                success = true
            )
        }
    }

}