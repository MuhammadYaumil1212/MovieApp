package yr.muhammadyaumil.movieapp.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import yr.muhammadyaumil.movieapp.core.constants.AppConstants
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.toEntity
import yr.muhammadyaumil.movieapp.data.local.Dao.FavoriteMovieDao
import yr.muhammadyaumil.movieapp.data.remote.MovieApiServices
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.MovieDetail
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity
import yr.muhammadyaumil.movieapp.domain.entity.Movie.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.Movie.NowPlayingMovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.ImageMovieEntity
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl
    @Inject
    constructor(
        private val movieApiServices: MovieApiServices,
        private val favDao: FavoriteMovieDao,
    ) : MovieRepository {
        override suspend fun getMovies(): Flow<Resources<MovieGeneralEntity>> =
            flow<Resources<MovieGeneralEntity>> {
                val moviesResponse =
                    movieApiServices
                        .getMovies()
                        .toEntity()

                if (moviesResponse.results != null) {
                    emit(Resources.Success(moviesResponse))
                }
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(e.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun getNowPlayingMovies(): Flow<Resources<NowPlayingMovieEntity>> =
            flow<Resources<NowPlayingMovieEntity>> {
                val nowPlayingMoviesResponse =
                    movieApiServices
                        .getNowPlayingMovies()
                if (nowPlayingMoviesResponse.results != null) {
                    emit(Resources.Success(nowPlayingMoviesResponse.toEntity()))
                }
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(e.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun getDetailMovie(movieId: Int): Flow<Resources<MovieDetail>> =
            flow<Resources<MovieDetail>> {
                val detailMovieResponse =
                    movieApiServices
                        .getDetailMovie(movieId)
                emit(Resources.Success(detailMovieResponse.toEntity()))
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(e.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun getImageMovie(movieId: Int): Flow<Resources<ImageMovieEntity>> =
            flow<Resources<ImageMovieEntity>> {
                val imageMovieResponse =
                    movieApiServices
                        .getMovieImages(movieId)
                emit(Resources.Success(imageMovieResponse.toEntity()))
            }.onStart {
                emit(Resources.Loading())
            }.catch {
                Log.e(AppConstants.API_LOGGER, it.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(it.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun isFavorite(movieId: Int): Flow<Resources<Boolean>> =
            favDao
                .isMovieFavorite(movieId)
                .map { isFavoriteStatus ->
                    Resources.Success(isFavoriteStatus) as Resources<Boolean>
                }.onStart {
                    emit(Resources.Loading())
                }.catch { e ->
                    Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                    emit(Resources.Error(e.localizedMessage ?: "Terjadi kesalahan"))
                }.flowOn(Dispatchers.IO)

        override suspend fun toggleFavorite(
            favorite: FavoriteEntity,
            isCurrentlyFavorite: Boolean,
        ): Flow<Resources<Unit>> =
            flow<Resources<Unit>> {
                if (isCurrentlyFavorite) {
                    favDao.deleteFavorite(favorite.movieId)
                    // TODO: Send Request Delete to API
                } else {
                    favDao.insertFavorite(favorite)
                    // TODO: Send Request Insert to API
                }
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                Log.e(AppConstants.API_LOGGER, e.localizedMessage ?: AppConstants.NULL_MESSAGE)
                emit(Resources.Error(e.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun getFavoriteMovies(): Flow<Resources<List<FavoriteEntity>>> =
            favDao
                .getAllFavoriteMovies()
                .map { allFavMovies ->
                    Resources.Success(allFavMovies) as Resources<List<FavoriteEntity>>
                }.onStart {
                    emit(Resources.Loading())
                }.catch {
                    Log.e(AppConstants.API_LOGGER, AppConstants.NULL_MESSAGE)
                    emit(Resources.Error(AppConstants.NULL_MESSAGE))
                }.flowOn(Dispatchers.IO)
    }
