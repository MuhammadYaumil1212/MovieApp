package yr.muhammadyaumil.movieapp.data.model.DetailMovie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreModel(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?
)