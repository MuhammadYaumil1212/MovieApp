package yr.muhammadyaumil.movieapp.ui.DetailHome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import yr.muhammadyaumil.movieapp.BuildConfig
import yr.muhammadyaumil.movieapp.core.composables.AppButton
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.utils.formatDate
import yr.muhammadyaumil.movieapp.ui.DetailHome.event.DetailHomeEvent
import yr.muhammadyaumil.movieapp.ui.DetailHome.state.DetailHomeState
import java.util.Locale

@Suppress("ktlint:standard:function-naming")
@Composable
fun DetailHomeScreen(
    modifier: Modifier,
    onEvent: (onEvent: DetailHomeEvent) -> Unit,
    navigateBack: () -> Unit,
    state: DetailHomeState,
) {
    AppScaffold(
        modifier = modifier,
        isLoading = state.isLoading,
        showErrorTextCenter = true,
        errorMessage = state.errorMessage,
        onErrorConsumed = {
            onEvent(DetailHomeEvent.ResetError)
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.Black),
        ) {
            AsyncImage(
                model = "${BuildConfig.IMAGE_URL}${state.detailMovie?.posterPath}",
                contentDescription = state.detailMovie?.title,
                contentScale = ContentScale.Crop,
                onLoading = { state.isLoading },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .align(Alignment.TopCenter),
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clickable { navigateBack() },
                )
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = Color.White,
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clickable { /* TODO: Bookmark action */ },
                )
            }

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.55f)
                        .align(Alignment.BottomCenter),
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                    color = Color.White,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(top = 36.dp),
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 32.dp, vertical = 24.dp),
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = state.detailMovie?.title ?: "",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.DarkGray,
                            )
                            Text(
                                state.detailMovie?.releaseDate.formatDate(),
                                fontSize = 18.sp,
                                color = Color.Gray,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Watch Time: ${state.detailMovie?.runtime} hours",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )

                        Text(
                            text =
                                state.detailMovie?.genres?.joinToString(separator = ", ") { it.name }
                                    ?: "",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = state.detailMovie?.overview ?: "No Overview",
                            fontSize = 15.sp,
                            color = Color.DarkGray,
                            lineHeight = 24.sp,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AppButton(
                                modifier =
                                    Modifier
                                        .weight(1f)
                                        .height(56.dp),
                                isLoading = state.buyTicketLoading,
                                onClick = {},
                                text = "But Ticket",
                            )
                            Spacer(modifier = Modifier.width(16.dp))

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier =
                                    Modifier
                                        .size(56.dp)
                                        .border(2.dp, Color.Gray, CircleShape),
                            ) {
                                Text(
                                    text =
                                        state.detailMovie?.voteAverage?.let {
                                            String.format(
                                                Locale.getDefault(),
                                                "%.1f",
                                                it,
                                            )
                                        } ?: "0.0",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Gray,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
