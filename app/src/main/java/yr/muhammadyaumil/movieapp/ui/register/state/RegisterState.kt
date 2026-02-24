package yr.muhammadyaumil.movieapp.ui.register.state

import androidx.compose.foundation.text.input.TextFieldState

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val username: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val confirmPassword: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
