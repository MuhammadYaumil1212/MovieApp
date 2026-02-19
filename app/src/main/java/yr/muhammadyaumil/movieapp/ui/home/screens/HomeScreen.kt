package yr.muhammadyaumil.movieapp.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import yr.muhammadyaumil.movieapp.BuildConfig
import yr.muhammadyaumil.movieapp.ui.home.composables.MovieRecentItem
import yr.muhammadyaumil.movieapp.ui.home.composables.MovieRecommendationItem
import yr.muhammadyaumil.movieapp.ui.home.viewmodel.HomeViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val state by homeViewModel.state.collectAsState()
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .safeContentPadding(),
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage ?: "Terjadi kesalahan",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            else -> {
                val movies = state.movieList?.results ?: emptyList()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    item {
                        Text(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                            text = "Now Playing",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                        )
                        LazyRow(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            items(movies.size) { index ->
                                MovieRecentItem(
                                    title = movies[index]?.originalTitle ?: "",
                                    rating = movies[index]?.voteAverage ?: 0.0,
                                    imageUrl = "${BuildConfig.IMAGE_URL}${movies[index]?.posterPath}",
                                )
                            }
                        }
                    }

                    item {
                        Text(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "Latest Movies",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                        )
                    }

                    items(movies.size) { index ->
                        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                            MovieRecommendationItem(
                                title = movies[index]?.originalTitle ?: "",
                                description = movies[index]?.overview ?: "",
                                duration = movies[index]?.releaseDate ?: "",
                                imageUrl = "${BuildConfig.IMAGE_URL}${movies[index]?.posterPath}",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
