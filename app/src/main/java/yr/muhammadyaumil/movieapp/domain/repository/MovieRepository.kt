@file:Suppress("ktlint:standard:filename")

package yr.muhammadyaumil.movieapp.domain.repository

import kotlinx.coroutines.flow.Flow
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.domain.entity.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.NowPlayingMovieEntity

interface MovieRepository {
    suspend fun getMovies(): Flow<Resources<MovieGeneralEntity>>

    suspend fun getNowPlayingMovies(): Flow<Resources<NowPlayingMovieEntity>>
}
