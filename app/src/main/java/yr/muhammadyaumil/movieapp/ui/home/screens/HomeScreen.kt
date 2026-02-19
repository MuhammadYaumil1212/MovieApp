package yr.muhammadyaumil.movieapp.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import yr.muhammadyaumil.movieapp.BuildConfig
import yr.muhammadyaumil.movieapp.ui.home.composables.MovieRecommendationItem
import yr.muhammadyaumil.movieapp.ui.home.viewmodel.HomeViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val state by homeViewModel.state.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage ?: "Terjadi kesalahan",
                    color = Color.Red,
                )
            }

            else -> {
                val movies = state.movieList?.results ?: emptyList()
                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                ) {
                    items(movies.size) { index ->
                        with(movies[index]) {
                            MovieRecommendationItem(
                                title = this?.originalTitle ?: "",
                                description = this?.overview ?: "",
                                duration = this?.releaseDate ?: "",
                                imageUrl =
                                    "${BuildConfig.IMAGE_URL}${this?.posterPath}",
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
