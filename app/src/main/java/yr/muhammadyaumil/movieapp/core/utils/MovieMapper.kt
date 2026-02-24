package yr.muhammadyaumil.movieapp.core.utils

import yr.muhammadyaumil.movieapp.data.model.Movie.MovieModel
import yr.muhammadyaumil.movieapp.data.model.Movie.NowPlayingMovieModel
import yr.muhammadyaumil.movieapp.data.model.Movie.ResultModel
import yr.muhammadyaumil.movieapp.data.model.Movie.ResultNowPlayingModel
import yr.muhammadyaumil.movieapp.domain.entity.MovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.NowPlayingMovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.ResultNowPlayingEntity

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
