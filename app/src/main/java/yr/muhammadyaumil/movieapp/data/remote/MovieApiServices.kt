package yr.muhammadyaumil.movieapp.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.result.PostgrestResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import yr.muhammadyaumil.movieapp.core.constants.ApiUrl
import yr.muhammadyaumil.movieapp.data.model.DetailMovie.MovieDetailModel
import yr.muhammadyaumil.movieapp.data.model.Movie.MovieModel
import yr.muhammadyaumil.movieapp.data.model.Movie.NowPlayingMovieModel
import yr.muhammadyaumil.movieapp.data.model.MovieImage.ImageMovieModel
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity
import javax.inject.Inject

interface MovieApiServices {
    suspend fun getMovies(): MovieModel

    suspend fun getNowPlayingMovies(): NowPlayingMovieModel

    suspend fun getDetailMovie(movieId: Int): MovieDetailModel

    suspend fun getMovieImages(movieId: Int): ImageMovieModel

    suspend fun insertMovie(insertMovieModel: FavoriteEntity): PostgrestResult

    suspend fun deleteMovie(
        movieId: Int,
        userId: String,
    ): PostgrestResult
}

class MovieApiServicesImpl
    @Inject
    constructor(
        private val client: HttpClient,
        private val supabaseClient: SupabaseClient,
    ) : MovieApiServices {
        override suspend fun getMovies(): MovieModel = client.get(ApiUrl.GET_MOVIES).body()

        override suspend fun getNowPlayingMovies(): NowPlayingMovieModel = client.get(ApiUrl.GET_NOW_PLAYING_MOVIES).body()

        override suspend fun getDetailMovie(movieId: Int): MovieDetailModel =
            client
                .get(
                    ApiUrl.GET_DETAIL_MOVIE,
                ) {
                    url {
                        appendPathSegments(movieId.toString())
                    }
                }.body()

        override suspend fun getMovieImages(movieId: Int): ImageMovieModel =
            client
                .get(
                    ApiUrl.GET_IMAGE_MOVIE.replace(
                        "{movieId}",
                        movieId.toString(),
                    ),
                ).body()

        override suspend fun insertMovie(insertMovieModel: FavoriteEntity) =
            supabaseClient
                .from("tb_favorite")
                .insert(insertMovieModel)

        override suspend fun deleteMovie(
            movieId: Int,
            userId: String,
        ): PostgrestResult =
            supabaseClient.from("tb_favorite").delete {
                filter {
                    eq("movie_id", movieId)
                    eq("user_id", userId)
                }
            }
    }
