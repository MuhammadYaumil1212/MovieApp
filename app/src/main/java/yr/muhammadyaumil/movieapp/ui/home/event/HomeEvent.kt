package yr.muhammadyaumil.movieapp.ui.home.event

sealed interface HomeEvent {
    data object ErrorConsumed : HomeEvent

    data object RefreshUi : HomeEvent

    data object OnSessionExpired : HomeEvent
}
