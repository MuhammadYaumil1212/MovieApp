package yr.muhammadyaumil.movieapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import yr.muhammadyaumil.movieapp.core.constants.ApiUrl
import yr.muhammadyaumil.movieapp.data.model.Movie.MovieModel
import yr.muhammadyaumil.movieapp.data.model.Movie.NowPlayingMovieModel
import javax.inject.Inject

interface MovieApiServices {
    suspend fun getMovies(): MovieModel

    suspend fun getNowPlayingMovies(): NowPlayingMovieModel
}

class MovieApiServicesImpl
    @Inject
    constructor(
        private val client: HttpClient,
    ) : MovieApiServices {
        override suspend fun getMovies(): MovieModel = client.get(ApiUrl.GET_MOVIES).body()

        override suspend fun getNowPlayingMovies(): NowPlayingMovieModel = client.get(ApiUrl.GET_NOW_PLAYING_MOVIES).body()
    }
