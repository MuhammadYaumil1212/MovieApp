package yr.muhammadyaumil.movieapp.core.utils

import yr.muhammadyaumil.movieapp.data.model.MovieModel
import yr.muhammadyaumil.movieapp.data.model.ResultModel
import yr.muhammadyaumil.movieapp.domain.entity.MovieEntity
import yr.muhammadyaumil.movieapp.domain.entity.MovieGeneralEntity

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
