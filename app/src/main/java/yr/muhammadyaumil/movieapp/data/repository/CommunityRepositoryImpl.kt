package yr.muhammadyaumil.movieapp.data.repository

import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.core.utils.toEntity
import yr.muhammadyaumil.movieapp.data.remote.CommunityApiService
import yr.muhammadyaumil.movieapp.domain.entity.Community.MovieCommentEntity
import yr.muhammadyaumil.movieapp.domain.entity.Community.MoviePostEntity
import yr.muhammadyaumil.movieapp.domain.repository.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl
    @Inject
    constructor(
        private val communityApiService: CommunityApiService,
    ) : CommunityRepository {
        override suspend fun getAllThreads(): Flow<Resources<List<MoviePostEntity>>> =
            flow<Resources<List<MoviePostEntity>>> {
                val allThreads =
                    communityApiService.getAllThreads().map { model ->
                        model.toEntity()
                    }
                if (allThreads.isNotEmpty()) {
                    emit(Resources.Success(allThreads))
                }
            }.onStart {
                emit(Resources.Loading())
            }.catch { error ->
                emit(Resources.Error(error.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun getAllComments(postId: String): Flow<Resources<List<MovieCommentEntity>>> =
            flow<Resources<List<MovieCommentEntity>>> {
                val allComments =
                    communityApiService.getAllComments(postId).map { model ->
                        model.toEntity()
                    }
                if (allComments.isNotEmpty()) {
                    emit(Resources.Success(allComments))
                }
            }.onStart {
                emit(Resources.Loading())
            }.catch { error ->
                emit(Resources.Error(error.localizedMessage))
            }.flowOn(Dispatchers.IO)

        override suspend fun postComment(
            postId: String,
            userId: String,
            content: String,
            parentId: String?,
        ): Flow<Resources<PostgrestResult>> =
            flow<Resources<PostgrestResult>> {
                val postComment = communityApiService.postComment(postId, userId, content, parentId)
                if (postComment.data.isNotEmpty()) {
                    emit(Resources.Success(postComment))
                }
            }.onStart {
                emit(Resources.Loading())
            }.catch { error ->
                emit(Resources.Error(error.localizedMessage))
            }.flowOn(Dispatchers.IO)
    }
