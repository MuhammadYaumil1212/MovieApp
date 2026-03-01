package yr.muhammadyaumil.movieapp.ui.profile.event

sealed interface ProfileEvent {
    data object ResetSucessState : ProfileEvent

    data object DismissError : ProfileEvent

    data object EditNameProfile : ProfileEvent

    data object EditEmailProfile : ProfileEvent

    data object EditPhoneProfile : ProfileEvent

    data object UpdateProfile : ProfileEvent

    data object ResetUsernameProfile : ProfileEvent

    data object ResetEmailProfile : ProfileEvent

    data object ResetPhoneProfile : ProfileEvent
}
