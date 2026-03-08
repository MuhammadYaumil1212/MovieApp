package yr.muhammadyaumil.movieapp.domain.entity.MovieImage

data class ImageMovieEntity(
    val backdrops: List<BackdropEntity?>?,
    val id: Int?,
    val logos: List<LogoEntity?>?,
    val posters: List<PosterEntity?>?,
)
