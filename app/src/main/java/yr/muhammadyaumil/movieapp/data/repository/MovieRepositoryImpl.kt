package yr.muhammadyaumil.movieapp.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import yr.muhammadyaumil.movieapp.core.constants.AppConstants
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.toEntity
import yr.muhammadyaumil.movieapp.data.remote.MovieApiServices
import yr.muhammadyaumil.movieapp.domain.entity.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.NowPlayingMovieEntity
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl
    @Inject
    constructor(
        private val movieApiServices: MovieApiServices,
    ) : MovieRepository {
        override suspend fun getMovies(): Flow<Resources<MovieGeneralEntity>> =
            flow {
                emit(Resources.Loading())
                val moviesResponse =
                    movieApiServices
                        .getMovies()
                        .toEntity()

                if (moviesResponse.results?.isNotEmpty() == true) {
                    emit(Resources.Success(moviesResponse))
                }
            }.flowOn(Dispatchers.IO).catch { e ->
                Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(e.localizedMessage))
            }

        override suspend fun getNowPlayingMovies(): Flow<Resources<NowPlayingMovieEntity>> =
            flow {
                emit(Resources.Loading())
                val nowPlayingMoviesResponse =
                    movieApiServices
                        .getNowPlayingMovies()
                if (nowPlayingMoviesResponse.results?.isNotEmpty() == true) {
                    emit(Resources.Success(nowPlayingMoviesResponse.toEntity()))
                }
            }.flowOn(Dispatchers.IO).catch { e ->
                Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(e.localizedMessage))
            }
    }
