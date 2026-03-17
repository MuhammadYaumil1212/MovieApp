package yr.muhammadyaumil.movieapp.ui.favorite.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.ui.favorite.composables.FavoriteItem
import yr.muhammadyaumil.movieapp.ui.favorite.event.FavoriteEvent
import yr.muhammadyaumil.movieapp.ui.favorite.state.FavoriteState

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    state: FavoriteState,
    onEvent: (onEvent: FavoriteEvent) -> Unit,
    onBackClick: () -> Unit,
) {
    AppScaffold(
        isLoading = false,
        errorMessage = state.errorMessage,
        showErrorTextCenter = state.errorMessage != null,
        onErrorConsumed = { onEvent(FavoriteEvent.DismissError) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Favorite", fontSize = 18.sp, fontWeight = FontWeight.W700)
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                        )
                    }
                },
                windowInsets = WindowInsets(0.dp),
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            items(state.getFavoriteMovies.size) {
                with(state.getFavoriteMovies[it]) {
                    FavoriteItem(
                        title = title,
                        description = descriptions,
                        duration = duration,
                        imageUrl = posterUrl,
                        onClick = {},
                    )
                }
            }
        }
    }
}
