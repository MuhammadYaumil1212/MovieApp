package yr.muhammadyaumil.movieapp.data.model.Movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InsertMovieModel(
    @SerialName("user_id")
    val userId: String,
    @SerialName("movie_id")
    val movieId: Long,
    val title: String,
    @SerialName("poster_url")
    val posterUrl: String,
    val descriptions: String,
    val duration: Long,
    @SerialName("is_favorite")
    val isFavorite: Boolean,
    @SerialName("addedAt")
    val addedAt: Double,
)
