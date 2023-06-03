package com.appbase.demoTest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appbase.redux.ApplicationState
import com.appbase.redux.Store
import com.appbase.utils.remote.ApiEvents
import com.appbase.utils.useCases.validateEmail
import com.appbase.utils.useCases.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    private val auth: Authentication,
) : ViewModel() {

    private var _formEventObserver = MutableStateFlow(LoginState())
    val formEventObserver = _formEventObserver
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailEvent -> _formEventObserver.update {
                it.copy(
                    email = event.email
                )
            }
            LoginEvent.Login -> login()
            is LoginEvent.PasswordEvent -> _formEventObserver.update {
                it.copy(
                    password = event.password
                )
            }
        }
    }

    private fun login() {
        val email = _formEventObserver.value.email.validateEmail()
        val password = _formEventObserver.value.password.validatePassword()
        val hasError = listOf(email, password).any { !it.success }

        _formEventObserver.update {
            it.copy(
                emailError = if (hasError) email.error else null,
                passwordError = if (hasError) password.error else null,
            )
        }

        if (hasError)
            return
        viewModelScope.launch {
            _formEventObserver.update {
                it.copy(
                    loading = true,
                    message = null
                )
            }

            val authResponse =
                auth.login(
                    email = _formEventObserver.value.email,
                    password = _formEventObserver.value.password
                )

            when (authResponse) {
                is ApiEvents.Failure<*> -> _formEventObserver.update {
                    it.copy(
                        loading = false,
                        success = false,
                        message = authResponse.error.toString()
                    )
                }
                is ApiEvents.Success<*> -> {
                    val data = authResponse.data
                    _formEventObserver.update {
                        it.copy(
                            loading = false,
                            success = true,
                            message = "$data"
                        )
                    }
                }

            }

        }
    }
}

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val loading: Boolean = false,
    val message: String? = null,
    val success: Boolean = false,
)