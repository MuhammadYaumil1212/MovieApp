package yr.muhammadyaumil.movieapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import yr.muhammadyaumil.movieapp.core.constants.ApiUrl
import yr.muhammadyaumil.movieapp.data.model.MovieModel
import javax.inject.Inject

interface MovieApiServices {
    suspend fun getMovies(): MovieModel
}

class MovieApiServicesImpl
    @Inject
    constructor(
        private val client: HttpClient,
    ) : MovieApiServices {
        override suspend fun getMovies(): MovieModel = client.get(ApiUrl.GET_MOVIES).body()
    }
