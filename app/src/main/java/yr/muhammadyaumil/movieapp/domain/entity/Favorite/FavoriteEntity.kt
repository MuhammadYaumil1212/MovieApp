package yr.muhammadyaumil.movieapp.domain.entity.Favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Entity(
    tableName = "favorite_movies",
    primaryKeys = ["user_id", "movie_id"],
)
@Serializable
data class FavoriteEntity(
    @ColumnInfo(name = "user_id") @SerialName("user_id") val userId: String,
    @ColumnInfo(name = "movie_id") @SerialName("movie_id") val movieId: Int,
    val title: String,
    @SerialName("poster_url")
    val posterUrl: String,
    val descriptions: String,
    val duration: Int,
    @SerialName("is_favorite")
    val isFavorite: Boolean = true,
    val addedAt: Long = System.currentTimeMillis(),
    @Transient
    val synced: Boolean = false,
)
