package yr.muhammadyaumil.movieapp.ui.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.data.local.sessions.SessionManager
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import yr.muhammadyaumil.movieapp.ui.favorite.event.FavoriteEvent
import yr.muhammadyaumil.movieapp.ui.favorite.state.FavoriteState
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
    @Inject
    constructor(
        private val getFavoriteMovieRepository: MovieRepository,
        private val sessionManager: SessionManager,
    ) : ViewModel() {
        private val _state = MutableStateFlow(FavoriteState())
        val state: StateFlow<FavoriteState> = _state.asStateFlow()

        init {
            observeFavoriteMovie()
        }

        fun onEvent(event: FavoriteEvent) {
            when (event) {
                is FavoriteEvent.DismissError -> {
                    _state.update { it.copy(errorMessage = null) }
                }

                is FavoriteEvent.AddFavorite -> {
                    toggleFavoriteMovies(movie = event.movieData, isCurrentlyFavorite = true)
                }

                is FavoriteEvent.DeleteFavorite -> {
                    toggleFavoriteMovies(movie = event.movieData, isCurrentlyFavorite = false)
                }
            }
        }

        private fun toggleFavoriteMovies(
            movie: FavoriteEntity,
            isCurrentlyFavorite: Boolean,
        ) = viewModelScope.launch {
            getFavoriteMovieRepository
                .toggleFavorite(
                    favorite = movie,
                    isCurrentlyFavorite = isCurrentlyFavorite,
                ).collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(loading = true)
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(
                                loading = false,
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(loading = false, errorMessage = message)
                        },
                    )
                }
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        private fun observeFavoriteMovie() =
            viewModelScope.launch {
                sessionManager
                    .activeUserIdFlow
                    .flatMapLatest { userId ->
                        if (userId != null) {
                            getFavoriteMovieRepository.getFavoriteMovies(userId)
                        } else {
                            flowOf(Resources.Success(emptyList()))
                        }
                    }.collect { resources ->
                        _state.handleResource(
                            resource = resources,
                            onLoading = { currentState ->
                                currentState.copy(loading = true)
                            },
                            onSuccess = { currentState, data ->
                                currentState.copy(
                                    loading = false,
                                    getFavoriteMovies = data ?: emptyList(),
                                )
                            },
                            onError = { currentState, message ->
                                currentState.copy(loading = false, errorMessage = message)
                            },
                        )
                    }
            }
    }
