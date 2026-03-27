package yr.muhammadyaumil.movieapp.ui.community.state

import yr.muhammadyaumil.movieapp.domain.entity.Community.MovieCommentEntity
import yr.muhammadyaumil.movieapp.domain.entity.Community.MoviePostEntity

data class CommunityState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val commentData: List<MovieCommentEntity> = emptyList(),
    val postData: List<MoviePostEntity> = emptyList(),
    val dataLoaded: String? = null,
)
