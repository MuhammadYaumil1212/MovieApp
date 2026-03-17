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

    @Query("DELETE FROM favorite_movies WHERE movieId = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE movieId = :id)")
    fun isMovieFavorite(id: Int): Flow<Boolean>

    @Query("SELECT * FROM favorite_movies ORDER BY addedAt DESC")
    fun getAllFavoriteMovies(): Flow<List<FavoriteEntity>>
}
