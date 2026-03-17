package yr.muhammadyaumil.movieapp.domain.entity.Favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteEntity(
    @PrimaryKey
    val movieId: Int,
    val title: String,
    val posterUrl: String,
    val descriptions: String,
    val duration: Int,
    val isFavorite: Boolean = true,
    val addedAt: Long = System.currentTimeMillis(),
)
