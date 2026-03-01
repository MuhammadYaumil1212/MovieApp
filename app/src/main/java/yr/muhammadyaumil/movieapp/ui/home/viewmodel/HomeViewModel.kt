package yr.muhammadyaumil.movieapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import yr.muhammadyaumil.movieapp.ui.home.event.HomeEvent
import yr.muhammadyaumil.movieapp.ui.home.state.HomeState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val movieRepository: MovieRepository,
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state: StateFlow<HomeState> = _state.asStateFlow()

        init {
            getMovies()
            getNowPlayingMovies()
            getCurrentUserInfo()
            observeSession()
        }

        fun onEvent(event: HomeEvent) {
            when (event) {
                is HomeEvent.ErrorConsumed -> {
                    _state.update { it.copy(errorMessage = null) }
                }

                is HomeEvent.RefreshUi -> {
                    getMovies()
                    getNowPlayingMovies()
                }

                is HomeEvent.OnSessionExpired -> {
                    _state.update {
                        it.copy(
                            userName = null,
                            isUserLoggedIn = false,
                        )
                    }
                }
            }
        }

        private fun observeSession() {
            viewModelScope.launch {
                authRepository.getSessionStatus().collect { resources ->
                    when (resources) {
                        is Resources.Success -> {
                            val status = resources.data
                            val isAuthenticated = status is SessionStatus.Authenticated
                            _state.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    sessionStatus = status,
                                    isUserLoggedIn = isAuthenticated,
                                    userName = if (isAuthenticated) currentState.userName else null,
                                )
                            }
                            if (isAuthenticated) {
                                getCurrentUserInfo()
                            }
                        }

                        is Resources.Error -> {
                            _state.update {
                                it.copy(isLoading = false, errorMessage = resources.message)
                            }
                        }

                        is Resources.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }
                    }
                }
            }
        }

        private fun getCurrentUserInfo() {
            viewModelScope.launch {
                authRepository.getUserInfo().collect { userResources ->
                    _state.handleResource(
                        resource = userResources,
                        onLoading = { currentState ->
                            currentState.copy(isLoading = true, errorMessage = null)
                        },
                        onSuccess = { currentState, data ->
                            val rawDisplayName =
                                data
                                    ?.userMetadata
                                    ?.get("display_name")
                                    ?.toString()
                                    ?.trim('"')
                            val name =
                                rawDisplayName?.takeIf { it.isNotBlank() }
                                    ?: data?.email?.substringBefore("@")
                                    ?: "Guest"
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null,
                                userName = name,
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(isLoading = false, errorMessage = message)
                        },
                    )
                }
            }
        }

        private fun getMovies() =
            viewModelScope.launch {
                movieRepository
                    .getMovies()
                    .collect { resources ->
                        _state.handleResource(
                            resource = resources,
                            onLoading = { currentState ->
                                currentState.copy(isLoading = true, errorMessage = null)
                            },
                            onSuccess = { currentState, data ->
                                currentState.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    movieList = data,
                                )
                            },
                            onError = { currentState, message ->
                                currentState.copy(isLoading = false, errorMessage = message)
                            },
                        )
                    }
            }

        private fun getNowPlayingMovies() =
            viewModelScope.launch {
                movieRepository.getNowPlayingMovies().collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(isLoading = true, errorMessage = null)
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null,
                                nowPlayingList = data,
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(isLoading = false, errorMessage = message)
                        },
                    )
                }
            }
    }
