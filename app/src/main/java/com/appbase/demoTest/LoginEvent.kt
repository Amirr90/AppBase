package com.appbase.demoTest

sealed class LoginEvent {
    data class EmailEvent(val email: String) : LoginEvent()
    data class PasswordEvent(val password: String) : LoginEvent()
    object Login : LoginEvent()
}