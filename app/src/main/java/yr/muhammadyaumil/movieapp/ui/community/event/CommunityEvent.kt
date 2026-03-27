package yr.muhammadyaumil.movieapp.ui.community.event

sealed interface CommunityEvent {
    data object OnRefresh : CommunityEvent
}
