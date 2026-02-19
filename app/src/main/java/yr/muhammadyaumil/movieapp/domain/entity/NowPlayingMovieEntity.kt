package yr.muhammadyaumil.movieapp.domain.entity

data class NowPlayingMovieEntity(
    val page: Int?,
    val results: List<ResultNowPlayingEntity>?,
    val totalPages: Int?,
    val totalResults: Int?,
)
