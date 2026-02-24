package yr.muhammadyaumil.movieapp.data.model.Movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatesModel(
    @SerialName("maximum")
    val maximum: String?,
    @SerialName("minimum")
    val minimum: String?,
)
