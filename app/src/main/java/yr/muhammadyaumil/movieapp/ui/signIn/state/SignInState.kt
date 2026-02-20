package yr.muhammadyaumil.movieapp.ui.signIn.state

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
)
