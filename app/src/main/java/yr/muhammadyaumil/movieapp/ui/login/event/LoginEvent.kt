package yr.muhammadyaumil.movieapp.ui.login.event

sealed interface LoginEvent {
    data object LoginClicked : LoginEvent

    data object ErrorConsumed : LoginEvent

    data object LoginNavigate : LoginEvent
}
