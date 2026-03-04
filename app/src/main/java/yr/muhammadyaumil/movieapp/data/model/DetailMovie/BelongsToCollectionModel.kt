package yr.muhammadyaumil.movieapp.data.model.DetailMovie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionModel(
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("poster_path")
    val posterPath: String?
)