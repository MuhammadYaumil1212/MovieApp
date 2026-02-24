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
                is RegisterEvent.ErrorConsumed -> {
                    _state.value = _state.value.copy(errorMessage = null)
                }

                is RegisterEvent.RegisterClicked -> {
                    onRegister()
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
            val confirmPass =
                _state.value.confirmPassword.text
                    .toString()
            if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                _state.value =
                    _state.value.copy(errorMessage = "Email, Password or Confirm Password cannot be blank")
                return
            }
            if (!pass.contains(confirmPass)) {
                _state.value =
                    _state.value.copy(errorMessage = "Password and Confirm Password must be same")
                return
            }
            viewModelScope.launch {
                authRepository.register(email, pass).collect { result ->
                    _state.handleResource(
                        resource = result,
                        onLoading = { currentState ->
                            currentState.copy(isLoading = true, errorMessage = null)
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(isLoading = false, errorMessage = null)
                        },
                        onError = { currentState, message ->
                            currentState.copy(isLoading = false, errorMessage = message)
                        },
                    )
                }
            }
        }
    }
