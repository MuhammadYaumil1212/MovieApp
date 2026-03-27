package yr.muhammadyaumil.movieapp.core.utils

import yr.muhammadyaumil.movieapp.data.model.Community.MovieComment
import yr.muhammadyaumil.movieapp.data.model.Community.MovieCommentRequest
import yr.muhammadyaumil.movieapp.data.model.Community.MoviePost
import yr.muhammadyaumil.movieapp.domain.entity.Community.MovieCommentEntity
import yr.muhammadyaumil.movieapp.domain.entity.Community.MovieCommentRequestEntity
import yr.muhammadyaumil.movieapp.domain.entity.Community.MoviePostEntity

fun MoviePost.toEntity(): MoviePostEntity =
    MoviePostEntity(
        tmdbMovieId = this.tmdbMovieId,
        userId = this.userId,
        content = this.content,
    )

fun MovieComment.toEntity(): MovieCommentEntity =
    MovieCommentEntity(
        id = this.id,
        postId = this.postId,
        userId = this.userId,
        content = this.content,
        parentCommentId = this.parentCommentId,
        createdAt = this.createdAt,
    )

fun MovieCommentRequest.toEntity(): MovieCommentRequestEntity =
    MovieCommentRequestEntity(
        postId = this.postId,
        userId = this.userId,
        content = this.content,
    )
