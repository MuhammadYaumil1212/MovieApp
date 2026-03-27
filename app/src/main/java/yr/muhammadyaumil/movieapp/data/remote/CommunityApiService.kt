package yr.muhammadyaumil.movieapp.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.result.PostgrestResult
import yr.muhammadyaumil.movieapp.data.model.Community.MovieComment
import yr.muhammadyaumil.movieapp.data.model.Community.MoviePost
import javax.inject.Inject

interface CommunityApiService {
    suspend fun getAllThreads(): List<MoviePost>

    suspend fun getAllComments(postId: String): List<MovieComment>

    suspend fun postComment(
        postId: String,
        userId: String,
        content: String,
        parentId: String? = null,
    ): PostgrestResult
}

class CommunityApiServiceImpl
    @Inject
    constructor(
        private val supabaseClient: SupabaseClient,
    ) : CommunityApiService {
        override suspend fun getAllThreads(): List<MoviePost> =
            supabaseClient
                .from("movie_posts")
                .select()
                .decodeList<MoviePost>()

        override suspend fun getAllComments(postId: String): List<MovieComment> =
            supabaseClient.postgrest["comment_post"]
                .select {
                    filter {
                        eq("post_id", postId)
                    }
                    order("created_at", Order.ASCENDING)
                }.decodeList<MovieComment>()

        override suspend fun postComment(
            postId: String,
            userId: String,
            content: String,
            parentId: String?,
        ) = supabaseClient.postgrest["comment_post"]
            .insert(content)
    }
