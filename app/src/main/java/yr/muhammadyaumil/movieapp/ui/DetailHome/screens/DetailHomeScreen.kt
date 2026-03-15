package yr.muhammadyaumil.movieapp.ui.DetailHome.screens

import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.utils.formatDate
import yr.muhammadyaumil.movieapp.core.utils.formatRating
import yr.muhammadyaumil.movieapp.ui.DetailHome.event.DetailHomeEvent
import yr.muhammadyaumil.movieapp.ui.DetailHome.state.DetailHomeState

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun DetailHomeScreen(
    modifier: Modifier = Modifier,
    onEvent: (onEvent: DetailHomeEvent) -> Unit,
    navigateBack: () -> Unit,
    state: DetailHomeState,
) {
    val scrollState = rememberScrollState()
    val genreScrollState = rememberScrollState()
    var isExpanded by remember { mutableStateOf(false) }
    var showReadMoreButton by remember { mutableStateOf(false) }

    val base64String = state.compressedMovieImage
    val imageByteArray =
        remember(base64String) {
            if (!base64String.isNullOrEmpty()) {
                try {
                    Base64.decode(base64String, Base64.DEFAULT)
                } catch (e: IllegalArgumentException) {
                    null
                }
            } else {
                null
            }
        }

    AppScaffold(
        modifier = modifier,
        isLoading = state.isLoading,
        showErrorTextCenter = true,
        errorMessage = state.errorMessage,
        onErrorConsumed = {
            onEvent(DetailHomeEvent.ResetError)
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(450.dp),
            ) {
                AsyncImage(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                    model = imageByteArray,
                    contentDescription = "Image Posters",
                    contentScale = ContentScale.Crop,
                    onLoading = { state.isLoading },
                )
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(40.dp)
                                .background(Color.Gray.copy(alpha = 0.4f), shape = CircleShape)
                                .clickable { navigateBack() },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                    Box(
                        modifier =
                            Modifier
                                .size(40.dp)
                                .background(Color.Gray.copy(alpha = 0.4f), shape = CircleShape)
                                .clickable { /* Handle Bookmark Event */ },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
                Box(
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 16.dp, bottom = 16.dp)
                            .size(48.dp)
                            .offset(y = 35.dp)
                            .shadow(elevation = 10.dp, shape = CircleShape)
                            .background(Color.White, shape = CircleShape)
                            .clickable { /* TODO:action when film loved */ },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier =
                            Modifier
                                .size(26.dp)
                                .height(450.dp),
                    )
                }
            }
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
            ) {
                Text(
                    text = state.detailMovie?.title ?: "Unknown Title",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 34.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        val rating = state.detailMovie?.voteAverage ?: 0.0
                        Text(
                            text = rating.formatRating(),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "/10",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.CalendarMonth,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = state.detailMovie?.releaseDate?.formatDate() ?: "-",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        val hours = state.detailMovie?.runtime?.div(60) ?: 0
                        val minutes = state.detailMovie?.runtime?.mod(60) ?: 0
                        Text(
                            text = "${hours}h ${minutes}m",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .horizontalScroll(genreScrollState),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    state.detailMovie?.genres?.forEach { genre ->
                        Box(
                            modifier =
                                Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                        shape = CircleShape,
                                    ).padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = genre.name,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Storyline",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = state.detailMovie?.overview ?: "No Overview available.",
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    lineHeight = 24.sp,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutRes ->
                        if (textLayoutRes.hasVisualOverflow) {
                            showReadMoreButton = true
                        }
                    },
                )
                if (showReadMoreButton) {
                    Text(
                        text = if (isExpanded) "Show less" else "Read more",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier =
                            Modifier
                                .padding(top = 4.dp)
                                .clickable { isExpanded = !isExpanded }
                                .padding(vertical = 4.dp),
                    )
                }
            }
        }
    }
}
