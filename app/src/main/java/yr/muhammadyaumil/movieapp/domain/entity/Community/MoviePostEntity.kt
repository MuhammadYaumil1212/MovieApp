package yr.muhammadyaumil.movieapp.domain.entity.Community

data class MoviePostEntity(
    val tmdbMovieId: Int,
    val userId: String,
    val username: String,
    val content: String,
)
