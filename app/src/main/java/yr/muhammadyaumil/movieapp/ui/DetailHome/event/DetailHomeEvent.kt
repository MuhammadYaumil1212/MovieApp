package yr.muhammadyaumil.movieapp.ui.DetailHome.event

sealed interface DetailHomeEvent {
    data object ResetError : DetailHomeEvent
}
