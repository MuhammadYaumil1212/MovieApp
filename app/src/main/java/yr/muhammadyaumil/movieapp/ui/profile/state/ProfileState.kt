package yr.muhammadyaumil.movieapp.ui.profile.state

import androidx.compose.foundation.text.input.TextFieldState
import io.github.jan.supabase.auth.user.UserInfo

data class ProfileState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val profileUrl: String = "",
    val userData: UserInfo? = null,
    val isUsernameEdit: Boolean = false,
    val isEmailEdit: Boolean = false,
    val isPhoneEdit: Boolean = false,
    val usernameState: TextFieldState = TextFieldState(),
    val emailState: TextFieldState = TextFieldState(),
    val phoneState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
)
