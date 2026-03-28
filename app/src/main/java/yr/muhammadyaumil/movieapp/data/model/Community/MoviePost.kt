package yr.muhammadyaumil.movieapp.data.model.Community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePost(
    @SerialName("tmdb_movie_id")
    val tmdbMovieId: Int,
    @SerialName("user_id")
    val userId: String,
    @SerialName("content")
    val content: String,
    @SerialName("username")
    val username: String,
    @SerialName("comment_count")
    val commentCount: Int,
    @SerialName("like_count")
    val likeCount: Int,
)
