package yr.muhammadyaumil.movieapp.ui.register.event

sealed interface RegisterEvent {
    data object RegisterClicked : RegisterEvent

    data object ErrorConsumed : RegisterEvent
}
