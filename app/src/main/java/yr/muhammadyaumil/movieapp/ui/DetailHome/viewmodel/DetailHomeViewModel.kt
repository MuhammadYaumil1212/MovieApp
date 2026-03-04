package yr.muhammadyaumil.movieapp.ui.DetailHome.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import yr.muhammadyaumil.movieapp.ui.DetailHome.event.DetailHomeEvent
import yr.muhammadyaumil.movieapp.ui.DetailHome.state.DetailHomeState
import javax.inject.Inject

@HiltViewModel
class DetailHomeViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val movieRepository: MovieRepository,
    ) : ViewModel() {
        private val movieId: Int = checkNotNull(savedStateHandle["movieId"])
        private val _state = MutableStateFlow(DetailHomeState())
        val state: StateFlow<DetailHomeState> = _state.asStateFlow()

        init {
            getDetailMovie(movieId)
        }

        fun onEvent(event: DetailHomeEvent) {
            when (event) {
                is DetailHomeEvent.ResetError -> {
                    _state.value = _state.value.copy(errorMessage = null)
                }
            }
        }

        private fun getDetailMovie(movieId: Int) {
            viewModelScope.launch {
                movieRepository.getDetailMovie(movieId = movieId).collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(isLoading = true, errorMessage = null)
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null,
                                detailMovie = data,
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
