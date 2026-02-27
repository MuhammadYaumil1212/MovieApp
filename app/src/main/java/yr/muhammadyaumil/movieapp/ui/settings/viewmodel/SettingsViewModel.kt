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
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import yr.muhammadyaumil.movieapp.ui.settings.event.SettingsEvent
import yr.muhammadyaumil.movieapp.ui.settings.state.SettingsState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
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
                    when (resources) {
                        is Resources.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }

                        is Resources.Success -> {
                            _state.value =
                                _state.value.copy(
                                    name =
                                        resources.data
                                            ?.userMetadata
                                            ?.get("Display Name")
                                            ?.toString()
                                            ?.takeIf { it.isNotBlank() } ?: resources.data?.email
                                            ?: "Name not found",
                                    phoneNumber =
                                        resources.data?.phone?.takeIf { it.isNotBlank() }
                                            ?: "Please verify your phone number",
                                    isLoading = false,
                                )
                        }

                        is Resources.Error -> {
                            _state.value =
                                _state.value.copy(
                                    errorMessage = resources.message ?: "Something Wrong",
                                    isLoading = false,
                                )
                        }
                    }
                }
            }

        private fun logout() =
            viewModelScope.launch {
                authRepository.logout().collect { resources ->
                    when (resources) {
                        is Resources.Success -> {
                            _state.value = _state.value.copy(isSuccess = true, logoutLoading = false)
                        }

                        is Resources.Loading -> {
                            _state.value = _state.value.copy(logoutLoading = true)
                        }

                        is Resources.Error -> {
                            _state.value =
                                _state.value.copy(
                                    errorMessage = resources.message,
                                    logoutLoading = false,
                                )
                        }
                    }
                }
            }
    }
