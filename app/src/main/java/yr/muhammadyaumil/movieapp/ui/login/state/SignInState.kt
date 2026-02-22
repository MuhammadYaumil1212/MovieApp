package yr.muhammadyaumil.movieapp.ui.login.state

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
)
