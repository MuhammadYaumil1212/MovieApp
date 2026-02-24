package yr.muhammadyaumil.movieapp.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface AuthApiServices {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    )

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    ): UserInfo?

    suspend fun authWithGoogle()

    suspend fun getCurrentUser(): UserInfo?

    suspend fun getSessionStatus(): StateFlow<SessionStatus>

    suspend fun logout()
}

class AuthApiServicesImpl
    @Inject
    constructor(
        private val client: SupabaseClient,
    ) : AuthApiServices {
        override suspend fun signInWithEmailAndPassword(
            email: String,
            password: String,
        ) = client.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }

        override suspend fun signUpWithEmailAndPassword(
            email: String,
            password: String,
        ) = client.auth.signUpWith(
            Email,
        ) {
            this.email = email
            this.password = password
        }

        override suspend fun authWithGoogle() {
            TODO("Not yet implemented")
        }

        override suspend fun getCurrentUser(): UserInfo? = client.auth.currentUserOrNull()

        override suspend fun getSessionStatus(): StateFlow<SessionStatus> = client.auth.sessionStatus

        override suspend fun logout() = client.auth.signOut()
    }
