package yr.muhammadyaumil.movieapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMovieModel(
    @SerialName("dates")
    val dates: DatesModel?,
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<ResultNowPlayingModel>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?,
)
