package yr.muhammadyaumil.movieapp.ui.detailHome.state

import android.net.Uri
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.MovieDetail
import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.ImageMovieEntity

data class DetailHomeState(
    val isLoading: Boolean = false,
    val favoriteLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val detailMovie: MovieDetail? = null,
    val favEntity: FavoriteEntity? = null,
    val movieImage: ImageMovieEntity? = null,
    val compressedMovieImage: String? = null,
    val errorMessage: String? = null,
    val isAuthenticated: Boolean = false,
    val selectedUri: Uri? = null,
    val compressData: ByteArray? = null,
    val isCompressing: Boolean = false,
)
