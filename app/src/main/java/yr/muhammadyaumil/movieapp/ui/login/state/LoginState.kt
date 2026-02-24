package yr.muhammadyaumil.movieapp.ui.login.state

import androidx.compose.foundation.text.input.TextFieldState

data class LoginState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
