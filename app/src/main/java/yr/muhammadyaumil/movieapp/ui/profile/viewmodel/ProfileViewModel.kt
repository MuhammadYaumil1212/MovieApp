package yr.muhammadyaumil.movieapp.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
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
