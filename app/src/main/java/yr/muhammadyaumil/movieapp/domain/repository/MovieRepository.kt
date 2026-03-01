@file:Suppress("ktlint:standard:filename")

package yr.muhammadyaumil.movieapp.domain.repository

import kotlinx.coroutines.flow.Flow
import yr.muhammadyaumil.movieapp.core.state.Resources
import yr.muhammadyaumil.movieapp.domain.entity.Movie.MovieGeneralEntity
import yr.muhammadyaumil.movieapp.domain.entity.Movie.NowPlayingMovieEntity

interface MovieRepository {
    suspend fun getMovies(): Flow<Resources<MovieGeneralEntity>>

    suspend fun getNowPlayingMovies(): Flow<Resources<NowPlayingMovieEntity>>
}
