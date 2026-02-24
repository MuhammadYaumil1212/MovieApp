package yr.muhammadyaumil.movieapp.domain.repository

import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.Flow
import yr.muhammadyaumil.movieapp.core.state.Resources

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String,
    ): Flow<Resources<Unit>>

    suspend fun register(
        email: String,
        password: String,
    ): Flow<Resources<Unit>>

    suspend fun getUserInfo(): Flow<Resources<UserInfo?>>

    suspend fun logout(): Flow<Resources<Unit>>
}
