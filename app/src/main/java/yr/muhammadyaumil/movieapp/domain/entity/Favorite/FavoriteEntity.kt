package yr.muhammadyaumil.movieapp.domain.entity.Favorite

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "favorite_movies",
    primaryKeys = ["user_id", "movie_id"],
)
data class FavoriteEntity(
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    val title: String,
    val posterUrl: String,
    val descriptions: String,
    val duration: Int,
    val isFavorite: Boolean = true,
    val addedAt: Long = System.currentTimeMillis(),
    val synced: Boolean = false,
)
