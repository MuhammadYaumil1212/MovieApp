package yr.muhammadyaumil.movieapp.data.model.MovieImage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageMovieModel(
    @SerialName("backdrops")
    val backdrops: List<BackdropModel?>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("logos")
    val logos: List<LogoModel?>? = null,
    @SerialName("posters")
    val posters: List<PosterModel?>? = null,
)
