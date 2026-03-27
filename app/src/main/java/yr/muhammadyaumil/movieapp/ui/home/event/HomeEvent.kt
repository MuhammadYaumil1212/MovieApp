package yr.muhammadyaumil.movieapp.ui.home.event

sealed interface HomeEvent {
    data object ResetError : HomeEvent

    data object RefreshUi : HomeEvent

    data object OnSessionExpired : HomeEvent

    data object OnRefresh : HomeEvent
}
