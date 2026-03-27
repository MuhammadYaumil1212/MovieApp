package yr.muhammadyaumil.movieapp.ui.community.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.utils.handleResource
import yr.muhammadyaumil.movieapp.data.model.Community.MovieComment
import yr.muhammadyaumil.movieapp.domain.entity.Community.CommentNode
import yr.muhammadyaumil.movieapp.domain.repository.CommunityRepository
import yr.muhammadyaumil.movieapp.ui.community.event.CommunityEvent
import yr.muhammadyaumil.movieapp.ui.community.state.CommunityState
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel
    @Inject
    constructor(
        private val communityRepository: CommunityRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CommunityState())
        val state: StateFlow<CommunityState> =
            _state
                .onStart {
                    getAllPost()
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000L),
                    initialValue = CommunityState(),
                )

        fun onEvent(event: CommunityEvent) {
            when (event) {
                is CommunityEvent.OnRefresh -> {
                    getAllPost()
                }
            }
        }

        private fun getAllPost() =
            viewModelScope.launch {
                communityRepository.getAllThreads().collect { resources ->
                    _state.handleResource(
                        resource = resources,
                        onLoading = { currentState ->
                            currentState.copy(
                                isLoading = true,
                            )
                        },
                        onSuccess = { currentState, data ->
                            currentState.copy(
                                isLoading = false,
                                postData = data ?: emptyList(),
                            )
                        },
                        onError = { currentState, message ->
                            currentState.copy(
                                isLoading = false,
                                errorMessage = message,
                            )
                        },
                    )
                }
            }

        fun buildCommentTree(
            comments: List<MovieComment>,
            parentId: String? = null,
        ): List<CommentNode> =
            comments
                .filter { it.parentCommentId == parentId }
                .map { CommentNode(it, buildCommentTree(comments, it.id)) }
    }
