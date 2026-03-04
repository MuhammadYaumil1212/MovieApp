package yr.muhammadyaumil.movieapp.ui.DetailHome.state

import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.MovieDetail

data class DetailHomeState(
    val isLoading: Boolean = false,
    val buyTicketLoading: Boolean = false,
    val detailMovie: MovieDetail? = null,
    val errorMessage: String? = null,
)
