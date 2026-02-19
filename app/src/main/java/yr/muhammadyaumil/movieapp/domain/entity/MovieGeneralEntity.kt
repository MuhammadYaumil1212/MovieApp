package yr.muhammadyaumil.movieapp.domain.entity

data class MovieGeneralEntity(
    val page: Int?,
    val results: List<MovieEntity?>?,
    val totalPages: Int?,
    val totalResults: Int?,
)
