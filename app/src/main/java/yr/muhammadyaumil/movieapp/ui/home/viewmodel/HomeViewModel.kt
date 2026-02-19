package yr.muhammadyaumil.movieapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import yr.muhammadyaumil.movieapp.ui.home.state.HomeState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val movieRepository: MovieRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state: StateFlow<HomeState> = _state.asStateFlow()

        init {
            getMovies()
            getNowPlayingMovies()
        }

        private fun getMovies() =
            viewModelScope.launch {
                movieRepository
                    .getMovies()
                    .collect { resources ->
                        when (resources) {
                            is Resources.Loading -> {
                                _state.update { it.copy(isLoading = true) }
                            }

                            is Resources.Success -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        movieList = resources.data,
                                    )
                                }
                            }

                            is Resources.Error -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = resources.message,
                                    )
                                }
                            }
                        }
                    }
            }

        private fun getNowPlayingMovies() =
            viewModelScope.launch {
                movieRepository.getNowPlayingMovies().collect { resources ->
                    when (resources) {
                        is Resources.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }

                        is Resources.Success -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    nowPlayingList = resources.data,
                                )
                            }
                        }

                        is Resources.Error -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = resources.message,
                                )
                            }
                        }
                    }
                }
            }
    }
