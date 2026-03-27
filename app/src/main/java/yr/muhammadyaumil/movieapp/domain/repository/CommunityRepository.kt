package yr.muhammadyaumil.movieapp.domain.repository

import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.coroutines.flow.Flow
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.domain.entity.Community.MovieCommentEntity
import yr.muhammadyaumil.movieapp.domain.entity.Community.MoviePostEntity

interface CommunityRepository {
    suspend fun getAllThreads(): Flow<Resources<List<MoviePostEntity>>>

    suspend fun getAllComments(postId: String): Flow<Resources<List<MovieCommentEntity>>>

    suspend fun postComment(
        postId: String,
        userId: String,
        content: String,
        parentId: String? = null,
    ): Flow<Resources<PostgrestResult>>
}
