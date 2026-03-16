package yr.muhammadyaumil.movieapp.ui.detailHome.state

import android.net.Uri
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.MovieDetail
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.ImageMovieEntity

data class DetailHomeState(
    val isLoading: Boolean = false,
    val buyTicketLoading: Boolean = false,
    val detailMovie: MovieDetail? = null,
    val movieImage: ImageMovieEntity? = null,
    val compressedMovieImage: String? = null,
    val errorMessage: String? = null,
    val selectedUri: Uri? = null,
    val compressData: ByteArray? = null,
    val isCompressing: Boolean = false,
)
