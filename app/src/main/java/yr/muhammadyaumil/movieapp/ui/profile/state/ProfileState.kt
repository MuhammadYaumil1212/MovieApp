package yr.muhammadyaumil.movieapp.ui.profile.state

import io.github.jan.supabase.auth.user.UserInfo

data class ProfileState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val profileUrl: String = "",
    val userData: UserInfo? = null,
)
