package yr.muhammadyaumil.movieapp.ui.settings.state

data class SettingsState(
    val name: String = "",
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val logoutLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val isAuthenticated: Boolean = false,
)
