package yr.muhammadyaumil.movieapp.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import yr.muhammadyaumil.movieapp.ui.login.event.LoginEvent
import yr.muhammadyaumil.movieapp.ui.login.state.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(LoginState())
        val state: StateFlow<LoginState> = _state.asStateFlow()

        fun onEvent(event: LoginEvent) {
            when (event) {
                is LoginEvent.DismissError -> {
                    _state.update { it.copy(errorMessage = null) }
                }

                is LoginEvent.SubmitLogin -> {
                    onLogin()
                }

                is LoginEvent.ResetSuccessState -> {
                    _state.update { it.copy(isSuccess = false) }
                }
            }
        }

        private fun onLogin() {
            val email =
                _state.value.email.text
                    .toString()
            val pass =
                _state.value.password.text
                    .toString()
            if (email.isEmpty() || pass.isEmpty()) {
                _state.update { it.copy(errorMessage = "Email or Password cannot be blank") }
                return
            }
            viewModelScope.launch {
                authRepository
                    .login(email = email, password = pass)
                    .collect { result ->
                        _state.handleResource(
                            resource = result,
                            onLoading = { currentState ->
                                currentState.copy(isLoading = true, errorMessage = null)
                            },
                            onSuccess = { currentState, data ->
                                currentState.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    isSuccess = true,
                                )
                            },
                            onError = { currentState, message ->
                                currentState.copy(isLoading = false, errorMessage = message)
                            },
                        )
                    }
            }
        }
    }
