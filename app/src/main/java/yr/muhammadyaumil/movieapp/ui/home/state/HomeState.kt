package yr.muhammadyaumil.movieapp.ui.home.state

import yr.muhammadyaumil.movieapp.domain.entity.MovieGeneralEntity

data class HomeState(
    val isLoading: Boolean = false,
    val movieList: MovieGeneralEntity? = null,
    val errorMessage: String? = null,
)
