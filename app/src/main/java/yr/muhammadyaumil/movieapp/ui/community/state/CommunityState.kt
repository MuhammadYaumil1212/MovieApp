package yr.muhammadyaumil.movieapp.ui.community.state

import yr.muhammadyaumil.movieapp.data.model.Community.MovieComment
import yr.muhammadyaumil.movieapp.data.model.Community.MoviePost

data class CommunityState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val commentData: List<MovieComment> = emptyList(),
    val postData: List<MoviePost> = emptyList(),
    val dataLoaded: String? = null,
)
