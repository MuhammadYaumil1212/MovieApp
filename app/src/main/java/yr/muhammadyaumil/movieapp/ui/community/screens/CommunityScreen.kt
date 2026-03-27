package yr.muhammadyaumil.movieapp.ui.community.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.ui.community.composables.ThreadItem
import yr.muhammadyaumil.movieapp.ui.community.composables.ThreadNotFound
import yr.muhammadyaumil.movieapp.ui.community.event.CommunityEvent
import yr.muhammadyaumil.movieapp.ui.community.state.CommunityState

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    onEvent: (onEvent: CommunityEvent) -> Unit = {},
    state: CommunityState,
) {
    AppScaffold(
        modifier = Modifier,
        isLoading = state.isLoading,
        showErrorTextCenter = true,
        errorMessage = state.errorMessage,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Community", fontWeight = FontWeight.Bold)
                },
                windowInsets = WindowInsets(0.dp),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Aksi ketika diklik */ },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Data",
                )
            }
        },
    ) {
        if (state.postData.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .safeDrawingPadding()
                    .padding(top = 16.dp),
            ) {
                items(count = state.postData.size) { index ->
                    with(state.postData[index]) {
                        ThreadItem(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                            username = "Muhammad Yaumil",
                            userImageUrl = "",
                            timeAgo = "2 jam yang lalu",
                            movieTag = "Inception (2010)",
                            threadTitle = "title",
                            threadBody = content,
                            commentsCount = 10,
                            onCommentClick = {
                                println("Clicked!")
                            },
                        )
                    }
                }
            }
        } else {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(),
                contentAlignment = Alignment.Center,
            ) {
                ThreadNotFound(
                    onRefreshClick = { onEvent(CommunityEvent.OnRefresh) },
                )
            }
        }
    }
}
