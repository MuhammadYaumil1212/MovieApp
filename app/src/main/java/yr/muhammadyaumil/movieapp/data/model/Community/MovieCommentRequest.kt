package yr.muhammadyaumil.movieapp.data.model.Community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCommentRequest(
    @SerialName("post_id") val postId: String,
    @SerialName("user_id") val userId: String,
    val content: String,
    @SerialName("parent_comment_id") val parentCommentId: String? = null,
)
