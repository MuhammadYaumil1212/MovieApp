package yr.muhammadyaumil.movieapp.core.utils

import yr.muhammadyaumil.movieapp.data.model.DetailMovie.BelongsToCollectionModel
import yr.muhammadyaumil.movieapp.data.model.DetailMovie.GenreModel
import yr.muhammadyaumil.movieapp.data.model.DetailMovie.MovieDetailModel
import yr.muhammadyaumil.movieapp.data.model.DetailMovie.ProductionCompanyModel
import yr.muhammadyaumil.movieapp.data.model.Movie.MovieModel
import yr.muhammadyaumil.movieapp.data.model.Movie.NowPlayingMovieModel
import yr.muhammadyaumil.movieapp.data.model.Movie.ResultModel
import yr.muhammadyaumil.movieapp.data.model.Movie.ResultNowPlayingModel
import yr.muhammadyaumil.movieapp.data.model.MovieImage.BackdropModel
import yr.muhammadyaumil.movieapp.data.model.MovieImage.ImageMovieModel
import yr.muhammadyaumil.movieapp.data.model.MovieImage.LogoModel
import yr.muhammadyaumil.movieapp.data.model.MovieImage.PosterModel
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.Genre
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.MovieCollection
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.MovieDetail
import yr.muhammadyaumil.movieapp.domain.entity.DetailMovie.ProductionCompany
import yr.muhammadyaumil.movieapp.domain.entity.Movie.MovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.Movie.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.Movie.NowPlayingMovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.Movie.ResultNowPlayingEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.BackdropEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.ImageMovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.LogoEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieImage.PosterEntity

fun MovieModel.toEntity(): MovieGeneralEntity =
    MovieGeneralEntity(
        page = this.page,
        results = this.results?.map { resultModel -> resultModel.toEntity() },
        totalPages = this.totalPages,
        totalResults = this.totalResults,
    )

fun ResultModel.toEntity(): MovieEntity =
    MovieEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )

fun NowPlayingMovieModel.toEntity(): NowPlayingMovieEntity =
    NowPlayingMovieEntity(
        page = this.page,
        results = this.results?.map { resultModel -> resultModel.toEntity() },
        totalPages = this.totalPages,
        totalResults = this.totalResults,
    )

fun ResultNowPlayingModel.toEntity(): ResultNowPlayingEntity =
    ResultNowPlayingEntity(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )

fun MovieDetailModel.toEntity(): MovieDetail =
    MovieDetail(
        id = this.id ?: 0,
        title = this.title ?: "Unknown Title",
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "No overview available.",
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        releaseDate = this.releaseDate ?: "",
        runtime = this.runtime ?: 0,
        voteAverage = this.voteAverage ?: 0.0,
        voteCount = this.voteCount ?: 0,
        genres = this.genres?.map { it?.toEntity() ?: Genre(0, "Unknown Genre") } ?: emptyList(),
        belongsToCollection = this.belongsToCollection?.toEntity(),
        productionCompanies =
            this.productionCompanies?.map {
                it?.toEntity() ?: ProductionCompany(
                    0,
                    "Unknown Company",
                    "",
                )
            } ?: emptyList(),
    )

fun BelongsToCollectionModel.toEntity(): MovieCollection =
    MovieCollection(
        id = this.id ?: 0,
        name = this.name ?: "",
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
    )

fun GenreModel.toEntity(): Genre =
    Genre(
        id = this.id ?: 0,
        name = this.name ?: "",
    )

fun ProductionCompanyModel.toEntity(): ProductionCompany =
    ProductionCompany(
        id = this.id ?: 0,
        name = this.name ?: "",
        logoPath = this.logoPath ?: "",
    )

fun ImageMovieModel.toEntity(): ImageMovieEntity =
    ImageMovieEntity(
        backdrops = this.backdrops?.map { it?.toEntity() },
        id = this.id,
        logos = this.logos?.map { it?.toEntity() },
        posters = this.posters?.map { it?.toEntity() },
    )

fun BackdropModel.toEntity(): BackdropEntity =
    BackdropEntity(
        aspectRatio = this.aspectRatio,
        filePath = this.filePath,
        height = this.height,
        iso31661 = this.iso31661,
        iso6391 = this.iso6391,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        width = this.width,
    )

fun PosterModel.toEntity(): PosterEntity =
    PosterEntity(
        aspectRatio = this.aspectRatio,
        height = this.height,
        iso31661 = this.iso31661,
        iso6391 = this.iso6391,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        width = this.width,
        filePath = this.filePath,
    )

fun LogoModel.toEntity(): LogoEntity =
    LogoEntity(
        aspectRatio = this.aspectRatio,
        filePath = this.filePath,
        height = this.height,
        iso31661 = this.iso31661,
        iso6391 = this.iso6391,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        width = this.width,
    )
