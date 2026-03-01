package yr.muhammadyaumil.movieapp.domain.entity.Movie

data class MovieGeneralEntity(
    val page: Int?,
    val results: List<MovieEntity?>?,
    val totalPages: Int?,
    val totalResults: Int?,
)
