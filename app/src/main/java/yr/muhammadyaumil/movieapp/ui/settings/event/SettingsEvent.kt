package yr.muhammadyaumil.movieapp.ui.settings.event

sealed interface SettingsEvent {
    data object onLogout : SettingsEvent

    data object ErrorConsumed : SettingsEvent
}
