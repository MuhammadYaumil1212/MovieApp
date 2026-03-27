package yr.muhammadyaumil.movieapp.domain.entity.Community

data class MovieCommentRequestEntity(
    val postId: String,
    val userId: String,
    val content: String,
    val parentCommentId: String? = null,
)
