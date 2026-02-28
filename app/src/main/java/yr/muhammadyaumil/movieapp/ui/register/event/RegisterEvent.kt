package yr.muhammadyaumil.movieapp.ui.register.event

sealed interface RegisterEvent {
    data object SubmitRegistration : RegisterEvent

    data object ResetSucessState : RegisterEvent

    data object DismissError : RegisterEvent
}
