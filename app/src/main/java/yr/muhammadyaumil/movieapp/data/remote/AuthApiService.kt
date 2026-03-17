package yr.muhammadyaumil.movieapp.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

interface AuthApiServices {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    )

    suspend fun signUpWithEmailAndPassword(
        email: String,
        username: String,
        password: String,
    ): UserInfo?

    suspend fun authWithGoogle()

    suspend fun getCurrentUser(): UserInfo?

    suspend fun getSessionStatus(): StateFlow<SessionStatus>

    suspend fun updateProfile(
        email: String,
        username: String,
        phoneNumber: String,
    ): UserInfo?

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
            username: String,
            password: String,
        ) = client.auth.signUpWith(
            Email,
        ) {
            this.email = email.trim()
            this.password = password.trim()
            this.data =
                buildJsonObject {
                    put("display_name", username)
                }
        }

        override suspend fun authWithGoogle() {
            TODO("Not yet implemented")
        }

        override suspend fun getCurrentUser(): UserInfo? = client.auth.currentUserOrNull()

        override suspend fun getSessionStatus(): StateFlow<SessionStatus> = client.auth.sessionStatus

        override suspend fun updateProfile(
            email: String,
            username: String,
            phoneNumber: String,
        ) = client.auth.updateUser {
            this.email = email
            this.phone = phoneNumber
            this.data =
                buildJsonObject {
                    put("display_name", username)
                }
        }

        override suspend fun logout() = client.auth.signOut()
    }
