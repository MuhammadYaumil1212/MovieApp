package yr.muhammadyaumil.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import yr.muhammadyaumil.movieapp.data.local.Dao.FavoriteMovieDao
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
