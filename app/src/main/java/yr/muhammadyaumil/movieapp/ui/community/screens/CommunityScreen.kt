package yr.muhammadyaumil.movieapp.ui.community.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.ui.community.composables.StoryItem
import yr.muhammadyaumil.movieapp.ui.community.composables.ThreadItem

@Suppress("ktlint:standard:function-naming")
@Composable
fun CommunityScreen(modifier: Modifier = Modifier) {
    AppScaffold {
        LazyColumn(Modifier.padding(top = 16.dp)) {
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    items(count = 10) {
                        StoryItem(
                            modifier = Modifier,
                            imageUrl = "",
                            name = "No Name",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
            items(count = 10) {
                ThreadItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    username = "Doni Saputra",
                    userImageUrl = "",
                    timeAgo = "2 jam yang lalu",
                    movieTag = "Inception (2010)",
                    threadTitle = "Teori Ending Inception, Gasingnya Jatuh Gak Sih?",
                    threadBody = "Wow gila banget nih film",
                    commentsCount = 10,
                    onCommentClick = {
                        println("Clicked!")
                    },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(backgroundColor = 0xFFFAFAFA, showBackground = true)
fun CommunityScreenPreview() {
    CommunityScreen()
}
