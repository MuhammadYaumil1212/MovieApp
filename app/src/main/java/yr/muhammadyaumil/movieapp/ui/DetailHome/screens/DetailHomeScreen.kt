package yr.muhammadyaumil.movieapp.ui.DetailHome.screens

import android.util.Base64
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.utils.formatDate
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
    val scaffoldState = rememberBottomSheetScaffoldState()

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
        topBar = {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clickable { navigateBack() },
                )
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Bookmark",
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clickable {},
                )
            }
        },
        onErrorConsumed = {
            onEvent(DetailHomeEvent.ResetError)
        },
    ) { innerPadding ->
        BottomSheetScaffold(
            modifier = Modifier.padding(innerPadding),
            scaffoldState = scaffoldState,
            sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            sheetPeekHeight = 15.dp,
            sheetContainerColor = Color.White,
            sheetContent = {
                FilmBottomSheets(
                    scrollState = scrollState,
                    state = state,
                )
            },
        ) {
            AsyncImage(
                model = imageByteArray,
                contentDescription = "Image Posters",
                contentScale = ContentScale.Fit,
                onLoading = { state.isLoading },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FilmBottomSheets(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    state: DetailHomeState,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showReadMoreButton by remember { mutableStateOf(false) }

    Column(
        modifier =
            modifier
                .fillMaxWidth(),
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f, fill = false)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 32.dp, vertical = 24.dp),
        ) {
            Text(
                text = state.detailMovie?.title ?: "",
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                color = Color.DarkGray,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.detailMovie?.releaseDate?.formatDate() ?: "",
                fontSize = 15.sp,
                maxLines = 1,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${state.detailMovie?.runtime?.div(60)} hours ${
                    state.detailMovie?.runtime?.mod(
                        60,
                    )
                } minutes",
                fontSize = 14.sp,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.detailMovie?.genres?.joinToString(separator = ", ") { it.name } ?: "",
                fontSize = 14.sp,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = state.detailMovie?.overview ?: "No Overview",
                fontSize = 15.sp,
                color = Color.DarkGray,
                lineHeight = 24.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                onTextLayout = { textLayoutRes ->
                    if (textLayoutRes.hasVisualOverflow) {
                        showReadMoreButton = true
                    }
                },
            )
            if (showReadMoreButton) {
                Text(
                    text = if (isExpanded) "Show less" else "Show more",
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
