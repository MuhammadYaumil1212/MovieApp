package yr.muhammadyaumil.movieapp.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: FavoriteEntity)

    @Query("DELETE FROM favorite_movies WHERE movie_id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM favorite_movies WHERE user_id = :activeUserId AND isFavorite = 1 ORDER BY addedAt DESC")
    fun getActiveUserFavorites(activeUserId: String): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE user_id = :userId AND movie_id = :movieId AND isFavorite = 1)")
    fun isMovieFavorite(
        userId: String,
        movieId: Int,
    ): Flow<Boolean>
}
