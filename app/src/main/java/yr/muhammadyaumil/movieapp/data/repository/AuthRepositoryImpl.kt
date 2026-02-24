package yr.muhammadyaumil.movieapp.data.repository

import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.toDynamicError
import yr.muhammadyaumil.movieapp.data.local.SessionManager
import yr.muhammadyaumil.movieapp.data.remote.AuthApiServices
import yr.muhammadyaumil.movieapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val authRemote: AuthApiServices,
        private val sessionManager: SessionManager,
    ) : AuthRepository {
        init {
            CoroutineScope(Dispatchers.IO).launch {
                authRemote.getSessionStatus().collect { status ->
                    when (status) {
                        is SessionStatus.Authenticated -> {
                            status.session.accessToken.let { token ->
                                sessionManager.saveAccessToken(token = token)
                            }
                        }

                        is SessionStatus.NotAuthenticated -> {
                            sessionManager.clearSession()
                        }

                        else -> {}
                    }
                }
            }
        }

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
                Resources.Success(userResult)
            }.onStart {
                emit(Resources.Loading())
            }.catch { e ->
                emit(Resources.Error(e.message))
            }.flowOn(Dispatchers.IO)

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
