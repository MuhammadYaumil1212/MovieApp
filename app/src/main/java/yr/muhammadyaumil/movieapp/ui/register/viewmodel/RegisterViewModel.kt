package yr.muhammadyaumil.movieapp.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import yr.muhammadyaumil.movieapp.ui.register.event.RegisterEvent
import yr.muhammadyaumil.movieapp.ui.register.state.RegisterState
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(RegisterState())
        val state: StateFlow<RegisterState> = _state.asStateFlow()

        fun onEvent(event: RegisterEvent) {
            when (event) {
                is RegisterEvent.DismissError -> {
                    _state.value = _state.value.copy(errorMessage = null)
                }

                is RegisterEvent.SubmitRegistration -> {
                    onRegister()
                }

                is RegisterEvent.ResetSucessState -> {
                    _state.value = _state.value.copy(isSuccess = false)
                }
            }
        }

        private fun onRegister() {
            val email =
                _state.value.email.text
                    .toString()
            val pass =
                _state.value.password.text
                    .toString()
            val username =
                _state.value.username.text
                    .toString()

            val phoneNumber =
                _state.value.phoneNumber.text
                    .toString()
            val confirmPass =
                _state.value.confirmPassword.text
                    .toString()
            if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() || username.isEmpty() || phoneNumber.isEmpty()) {
                _state.value =
                    _state.value.copy(errorMessage = "Email, Password, Username,Phone Number or Confirm Password cannot be blank")
                return
            }
            if (!pass.contains(confirmPass)) {
                _state.value =
                    _state.value.copy(errorMessage = "Password and Confirm Password must be same")
                return
            }
            viewModelScope.launch {
                authRepository
                    .register(
                        email = email,
                        password = pass,
                        username = username,
                        phoneNumber = phoneNumber,
                    ).collect { resources ->
                        _state.handleResource(
                            resource = resources,
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
