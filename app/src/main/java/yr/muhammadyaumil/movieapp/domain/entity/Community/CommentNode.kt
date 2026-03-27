package yr.muhammadyaumil.movieapp.domain.entity.Community

import yr.muhammadyaumil.movieapp.data.model.Community.MovieComment

data class CommentNode(
    val comment: MovieComment,
    val replies: List<CommentNode>,
)
