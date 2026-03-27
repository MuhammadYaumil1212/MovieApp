package yr.muhammadyaumil.movieapp.domain.entity.Community

data class MovieCommentEntity(
    val id: String,
    val postId: String,
    val userId: String,
    val content: String,
    val parentCommentId: String? = null,
    val createdAt: String,
)
