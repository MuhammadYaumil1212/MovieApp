package yr.muhammadyaumil.movieapp.ui.login.event

sealed interface LoginEvent {
    data object SubmitLogin : LoginEvent

    data object DismissError : LoginEvent

    data object ResetSuccessState : LoginEvent
}
