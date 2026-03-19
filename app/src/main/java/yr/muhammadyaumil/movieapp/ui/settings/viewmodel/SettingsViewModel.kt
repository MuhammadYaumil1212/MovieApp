package yr.muhammadyaumil.movieapp.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.data.local.sessions.SessionManager
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import yr.muhammadyaumil.movieapp.ui.settings.event.SettingsEvent
import yr.muhammadyaumil.movieapp.ui.settings.state.SettingsState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
        private val sessionManager: SessionManager,
    ) : ViewModel() {
        private val _state = MutableStateFlow(SettingsState())
        val state: StateFlow<SettingsState> = _state.asStateFlow()

        init {
            observeSession()
            getInfoUser()
        }

        fun onEvent(event: SettingsEvent) {
            when (event) {
                is SettingsEvent.onLogout -> {
                    logout()
                }

                is SettingsEvent.ErrorConsumed -> {
                    _state.value = _state.value.copy(errorMessage = null)
                }
            }
        }

        private fun observeSession() =
            viewModelScope.launch {
                authRepository.getSessionStatus().collect { resources ->
                    val status = resources.data
                    val isAuthenticated = status is SessionStatus.Authenticated
                    when (resources) {
                        is Resources.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }

                        is Resources.Success -> {
//                            sessionManager.clearSession()
                            _state.value =
                                _state.value.copy(
                                    isSuccess = true,
                                    isLoading = false,
                                    isAuthenticated = isAuthenticated,
                                )
                        }

                        is Resources.Error -> {
                            _state.value =
                                _state.value.copy(errorMessage = resources.message, isLoading = false)
                        }
                    }
                }
            }

        private fun getInfoUser() =
            viewModelScope.launch {
                authRepository.getUserInfo().collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = {
                            _state.value.copy(isLoading = true)
                        },
                        onSuccess = { state, data ->
                            _state.value.copy(
                                name =
                                    data
                                        ?.userMetadata
                                        ?.get("Display Name")
                                        ?.toString()
                                        ?.takeIf { it.isNotBlank() } ?: data?.email
                                        ?: "Name not found",
                                phoneNumber =
                                    data?.phone?.takeIf { it.isNotBlank() }
                                        ?: "Please verify your phone number",
                                isLoading = false,
                            )
                        },
                        onError = { state, message ->
                            _state.value.copy(
                                isLoading = false,
                                errorMessage = message ?: "Something Wrong",
                            )
                        },
                    )
                }
            }

        private fun logout() =
            viewModelScope.launch {
                authRepository.logout().collect { resources ->
                    if (resources is Resources.Success) {
                        sessionManager.clearSession()
                    }
                    _state.handleResource(
                        resource = resources,
                        onLoading = {
                            _state.value.copy(logoutLoading = true)
                        },
                        onSuccess = { state, data ->
                            _state.value.copy(isSuccess = true, logoutLoading = false)
                        },
                        onError = { state, message ->
                            _state.value.copy(
                                isLoading = false,
                                errorMessage = message ?: "Something Wrong",
                            )
                        },
                    )
                }
            }
    }
