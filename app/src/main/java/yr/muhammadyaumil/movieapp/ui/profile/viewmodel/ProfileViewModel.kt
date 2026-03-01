package yr.muhammadyaumil.movieapp.ui.profile.viewmodel

import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import yr.muhammadyaumil.movieapp.ui.profile.event.ProfileEvent
import yr.muhammadyaumil.movieapp.ui.profile.state.ProfileState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state: StateFlow<ProfileState> = _state.asStateFlow()

        init {
            getCurrentUserInfo()
        }

        fun onEvent(event: ProfileEvent) {
            when (event) {
                is ProfileEvent.ResetSucessState -> {
                    _state.update { it.copy(isSuccess = false) }
                }

                is ProfileEvent.DismissError -> {
                    _state.update { it.copy(errorMessage = null) }
                }

                is ProfileEvent.EditNameProfile -> {
                    _state.update { it.copy(isUsernameEdit = true) }
                    val currentName =
                        _state.value.userData
                            ?.userMetadata
                            ?.get("display_name")
                            ?.toString()
                            ?.trim('"')
                            ?: ""
                    _state.value.usernameState.setTextAndPlaceCursorAtEnd(currentName)
                }

                is ProfileEvent.EditEmailProfile -> {
                    _state.update { it.copy(isEmailEdit = true) }
                    val currentEmail = _state.value.userData?.email ?: ""
                    _state.value.emailState.setTextAndPlaceCursorAtEnd(currentEmail)
                }

                is ProfileEvent.EditPhoneProfile -> {
                    _state.update { it.copy(isPhoneEdit = true) }
                    val currentPhone = _state.value.userData?.phone ?: ""
                    _state.value.phoneState.setTextAndPlaceCursorAtEnd(currentPhone)
                }

                is ProfileEvent.ResetUsernameProfile -> {
                    _state.update { it.copy(isUsernameEdit = false) }
                }

                is ProfileEvent.ResetEmailProfile -> {
                    _state.update { it.copy(isEmailEdit = false) }
                }

                is ProfileEvent.ResetPhoneProfile -> {
                    _state.update { it.copy(isPhoneEdit = false) }
                }

                is ProfileEvent.UpdateProfile -> {
                    updateUserInfo()
                }
            }
        }

        private fun updateUserInfo() =
            viewModelScope.launch {
                val email: String =
                    _state.value.emailState.text
                        .toString()
                val username: String =
                    _state.value.usernameState.text
                        .toString()
                val phone: String =
                    _state.value.phoneState.text
                        .toString()

                authRepository
                    .updateProfile(email = email, username = username, phoneNumber = phone)
                    .collect { resources ->
                        _state.handleResource(
                            resource = resources,
                            onLoading = { currentState ->
                                currentState.copy(isLoading = true, errorMessage = null)
                            },
                            onSuccess = { currentState, data ->
                                getCurrentUserInfo()
                                currentState.copy(
                                    isLoading = false,
                                    successMessage = "Profile Updated",
                                    isSuccess = true,
                                )
                            },
                            onError = { currentState, message ->
                                currentState.copy(isLoading = false, errorMessage = message)
                            },
                        )
                    }
            }

        private fun getCurrentUserInfo() =
            viewModelScope.launch {
                authRepository.getUserInfo().collect { resources ->
                    when (resources) {
                        is Resources.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }

                        is Resources.Success -> {
                            _state.update { it.copy(userData = resources.data, isLoading = false) }
                        }

                        is Resources.Error -> {
                            _state.update {
                                it.copy(
                                    errorMessage = resources.message,
                                    isLoading = false,
                                )
                            }
                        }
                    }
                }
            }
    }
