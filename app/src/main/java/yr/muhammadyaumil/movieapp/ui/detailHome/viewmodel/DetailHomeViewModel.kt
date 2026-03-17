package yr.muhammadyaumil.movieapp.ui.detailHome.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.BitmapImage
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yr.muhammadyaumil.movieapp.BuildConfig
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import yr.muhammadyaumil.movieapp.ui.detailHome.event.DetailHomeEvent
import yr.muhammadyaumil.movieapp.ui.detailHome.state.DetailHomeState
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class DetailHomeViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val movieRepository: MovieRepository,
        @ApplicationContext private val context: Context,
    ) : ViewModel() {
        private val movieId: Int = checkNotNull(savedStateHandle["movieId"])
        private val _state = MutableStateFlow(DetailHomeState())
        val state: StateFlow<DetailHomeState> = _state.asStateFlow()

        init {
            getDetailMovie(movieId)
            getImageMovie(movieId)
            isMovieFavorite(movieId)
        }

        fun onEvent(event: DetailHomeEvent) {
            when (event) {
                is DetailHomeEvent.ResetError -> {
                    _state.value = _state.value.copy(errorMessage = null)
                }

                is DetailHomeEvent.ToggleFavorite -> {
                    val currentMovie = _state.value.detailMovie
                    val isCurrentlyFavorite = _state.value.isFavorite
                    if (currentMovie != null) {
                        val favoriteEntity =
                            FavoriteEntity(
                                movieId = currentMovie.id,
                                title = currentMovie.title,
                                descriptions = currentMovie.overview,
                                duration = currentMovie.runtime,
                                posterUrl = "${BuildConfig.IMAGE_URL}/w200/${currentMovie.backdropPath}",
                            )

                        toggleFavoriteMovies(
                            movie = favoriteEntity,
                            isCurrentlyFavorite = isCurrentlyFavorite,
                        )
                    }
                }
            }
        }

        private fun toggleFavoriteMovies(
            movie: FavoriteEntity,
            isCurrentlyFavorite: Boolean,
        ) = viewModelScope.launch {
            movieRepository
                .toggleFavorite(
                    favorite = movie,
                    isCurrentlyFavorite = isCurrentlyFavorite,
                ).collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(favoriteLoading = true)
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(
                                favoriteLoading = false,
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(favoriteLoading = false, errorMessage = message)
                        },
                    )
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

        private fun isMovieFavorite(movieId: Int) =
            viewModelScope.launch {
                movieRepository.isFavorite(movieId = movieId).collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(favoriteLoading = true)
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(
                                favoriteLoading = false,
                                isFavorite = data ?: false,
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(
                                favoriteLoading = false,
                                errorMessage = message,
                            )
                        },
                    )
                }
            }

        private suspend fun compressRemoteImage(
            context: Context,
            imageUrl: String,
        ): String? =
            withContext(Dispatchers.IO) {
                try {
                    val loader = ImageLoader(context)
                    val request =
                        ImageRequest
                            .Builder(context)
                            .data(imageUrl)
                            .allowHardware(false)
                            .build()
                    val result = (loader.execute(request) as? SuccessResult)?.image
                    val bitmap = (result as? BitmapImage)?.bitmap

                    if (bitmap != null) {
                        val outputStream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
                        val compressedBytes = outputStream.toByteArray()
                        return@withContext Base64.encodeToString(compressedBytes, Base64.DEFAULT)
                    } else {
                        return@withContext null
                    }
                } catch (e: Exception) {
                    return@withContext null
                }
            }

        private fun getImageMovie(movieId: Int) =
            viewModelScope.launch {
                movieRepository.getImageMovie(movieId = movieId).collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(isLoading = true, errorMessage = null)
                        },
                        onSuccess = { currentState, data ->
                            val firstImage =
                                "${BuildConfig.IMAGE_URL}original${data?.posters?.first()?.filePath}"
                            viewModelScope.launch {
                                val compressedImage =
                                    compressRemoteImage(context = context, imageUrl = firstImage)
                                _state.update {
                                    it.copy(compressedMovieImage = compressedImage)
                                }
                            }
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null,
                                movieImage = data,
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(isLoading = false, errorMessage = message)
                        },
                    )
                }
            }
    }
