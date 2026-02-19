package yr.muhammadyaumil.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import yr.muhammadyaumil.movieapp.core.constants.AppConstants
import yr.muhammadyaumil.movieapp.data.remote.MovieApiServices
import yr.muhammadyaumil.movieapp.data.remote.MovieApiServicesImpl
import yr.muhammadyaumil.movieapp.data.repository.MovieRepositoryImpl
import yr.muhammadyaumil.movieapp.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClient(
            OkHttp.create(),
        ) {
            install(Logging) {
                level = LogLevel.BODY
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }
            defaultRequest {
                url(AppConstants.BASE_URL)
                url {
                    parameters.append("api_key", AppConstants.API_KEY)
                }
            }
        }

    @Provides
    @Singleton
    fun provideMovieApiServices(client: HttpClient): MovieApiServices = MovieApiServicesImpl(client)

    @Provides
    @Singleton
    fun provideMovieRepository(movieApiServices: MovieApiServices): MovieRepository =
        MovieRepositoryImpl(movieApiServices = movieApiServices)
}
