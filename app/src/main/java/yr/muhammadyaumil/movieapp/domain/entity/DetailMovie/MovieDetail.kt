package yr.muhammadyaumil.movieapp.domain.entity.DetailMovie

data class MovieDetail(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<Genre>,
    val belongsToCollection: MovieCollection?,
    val productionCompanies: List<ProductionCompany>,
)
