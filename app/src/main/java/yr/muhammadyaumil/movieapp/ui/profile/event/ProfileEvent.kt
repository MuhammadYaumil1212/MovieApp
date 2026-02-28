package yr.muhammadyaumil.movieapp.ui.profile.event

sealed interface ProfileEvent {
    data object ResetSucessState : ProfileEvent

    data object DismissError : ProfileEvent
}
