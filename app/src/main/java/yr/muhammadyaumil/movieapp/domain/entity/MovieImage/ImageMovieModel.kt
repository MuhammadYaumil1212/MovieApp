package yr.muhammadyaumil.movieapp.domain.entity.MovieImage


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageMovieModel(
    @SerialName("backdrops")
    val backdrops: List<BackdropModel?>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("logos")
    val logos: List<LogoModel?>?,
    @SerialName("posters")
    val posters: List<PosterModel?>?
)