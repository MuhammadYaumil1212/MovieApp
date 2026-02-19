package yr.muhammadyaumil.movieapp.ui.home.state

import yr.muhammadyaumil.movieapp.domain.entity.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.NowPlayingMovieEntity

data class HomeState(
    val isLoading: Boolean = false,
    val movieList: MovieGeneralEntity? = null,
    val nowPlayingList: NowPlayingMovieEntity? = null,
    val errorMessage: String? = null,
)
