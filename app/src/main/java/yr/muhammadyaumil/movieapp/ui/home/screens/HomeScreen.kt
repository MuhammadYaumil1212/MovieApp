package yr.muhammadyaumil.movieapp.ui.home.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.BuildConfig
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.utils.formatDate
import yr.muhammadyaumil.movieapp.core.utils.formatRating
import yr.muhammadyaumil.movieapp.ui.home.composables.MovieNotFound
import yr.muhammadyaumil.movieapp.ui.home.composables.MovieRecentItem
import yr.muhammadyaumil.movieapp.ui.home.composables.MovieRecommendationItem
import yr.muhammadyaumil.movieapp.ui.home.event.HomeEvent
import yr.muhammadyaumil.movieapp.ui.home.state.HomeState

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    modifier: Modifier,
    navigateToLogin: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToDetail: (movieId: Int) -> Unit,
    onEvent: (onEvent: HomeEvent) -> Unit,
    state: HomeState,
) {
    AppScaffold(
        modifier = modifier,
        isLoading = state.isLoading,
        showErrorTextCenter = true,
        errorMessage = state.errorMessage,
        onErrorConsumed = {
            onEvent(HomeEvent.ResetError)
        },
    ) {
        val movies = state.movieList?.results ?: emptyList()
        val nowPlayingMovies = state.nowPlayingList?.results ?: emptyList()
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            // item for Headers
            item {
                Row(
                    modifier =
                        Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Welcome back,",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                        )
                        Text(
                            text = state.userName ?: "Guest",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier =
                            Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    if (state.isUserLoggedIn == false) {
                                        navigateToLogin()
                                    } else {
                                        navigateToProfile()
                                    }
                                },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            // item for header now playing
            item {
                Text(
                    modifier = modifier.padding(horizontal = 16.dp),
                    text = "Now Playing",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            // item for now playing list
            if (nowPlayingMovies.isNotEmpty()) {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                    ) {
                        items(nowPlayingMovies.size) { index ->
                            with(nowPlayingMovies[index]) {
                                MovieRecentItem(
                                    title = this.originalTitle ?: "",
                                    rating =
                                        this.voteAverage
                                            .formatRating()
                                            .toDouble(),
                                    imageUrl = "${BuildConfig.IMAGE_URL}/w200/${this.posterPath}",
                                    onClick = {
                                        navigateToDetail(this.id ?: 0)
                                    },
                                )
                            }
                        }
                    }
                }
            } else {
                item {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(),
                        contentAlignment = Alignment.Center,
                    ) {
                        MovieNotFound(
                            onRefreshClick = { onEvent(HomeEvent.OnRefresh) },
                            msg = "Belum ada Film yang sedang ditampilkan",
                        )
                    }
                }
            }
            // item for header latest movies
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = modifier.padding(horizontal = 16.dp),
                    text = "Latest Movies",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                )
            }
            // item for list of latest movies
            if (movies.isNotEmpty()) {
                items(movies.size) { index ->
                    with(movies[index]) {
                        MovieRecommendationItem(
                            title = this?.title ?: "No Title",
                            description = this?.overview ?: "No Descriptions",
                            duration = this?.releaseDate.formatDate(),
                            imageUrl = "${BuildConfig.IMAGE_URL}/w200/${this?.posterPath}",
                            onClick = {
                                navigateToDetail(this?.id ?: 0)
                            },
                        )
                    }
                }
            } else {
                item {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(),
                        contentAlignment = Alignment.Center,
                    ) {
                        MovieNotFound(
                            onRefreshClick = { onEvent(HomeEvent.OnRefresh) },
                            msg = "Belum ada Film yang sedang ditampilkan",
                        )
                    }
                }
            }
        }
    }
}
