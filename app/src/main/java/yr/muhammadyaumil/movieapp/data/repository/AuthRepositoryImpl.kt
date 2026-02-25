package yr.muhammadyaumil.movieapp.data.repository

import android.util.Log
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.toDynamicError
import yr.muhammadyaumil.movieapp.data.remote.AuthApiServices
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val authRemote: AuthApiServices,
    ) : AuthRepository {
        override suspend fun login(
            email: String,
            password: String,
        ): Flow<Resources<Unit>> =
            flow<Resources<Unit>> {
                authRemote.signInWithEmailAndPassword(email, password)
                emit(Resources.Success(Unit))
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                emit(Resources.Error(e.toDynamicError()))
            }.flowOn(Dispatchers.IO)

        override suspend fun register(
            email: String,
            password: String,
        ): Flow<Resources<Unit>> =
            flow<Resources<Unit>> {
                authRemote.signUpWithEmailAndPassword(email, password)
                emit(Resources.Success(Unit))
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                emit(Resources.Error(e.toDynamicError()))
            }.flowOn(Dispatchers.IO)

        override suspend fun getUserInfo(): Flow<Resources<UserInfo?>> =
            flow<Resources<UserInfo?>> {
                val userResult = authRemote.getCurrentUser()
                Log.d("USER_INFO", "${userResult?.email}")
                emit(Resources.Success(userResult))
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                emit(Resources.Error(e.message))
            }.flowOn(Dispatchers.IO)

        override suspend fun getSessionStatus(): Flow<Resources<SessionStatus>> =
            authRemote
                .getSessionStatus()
                .map { status ->
                    Resources.Success(status) as Resources<SessionStatus>
                }.onStart {
                    emit(Resources.Loading())
                }.catch { e ->
                    emit(Resources.Error(e.message ?: "Gagal memuat status sesi"))
                }

        override suspend fun logout() =
            flow<Resources<Unit>> {
                authRemote.logout()
                emit(Resources.Success(Unit))
            }.onStart {
                emit(Resources.Loading())
            }.catch {
                emit(Resources.Error(it.message))
            }.flowOn(Dispatchers.IO)
    }
