package yr.muhammadyaumil.movieapp.ui.Community.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Suppress("ktlint:standard:function-naming")
@Composable
fun ThreadItem(
    modifier: Modifier = Modifier,
    username: String = "Reza Rahadian",
    userImageUrl: String = "",
    timeAgo: String = "2 jam yang lalu",
    movieTag: String = "Inception (2010)",
    threadTitle: String = "Teori Ending Inception, Gasingnya Jatuh Gak Sih?",
    threadBody: String = "Habis nonton ulang semalam, aku masih kepikiran sama endingnya. Menurut kalian Cobb beneran udah bangun atau masih di dalam mimpi level terdalam?",
    likesCount: Int = 142,
    commentsCount: Int = 38,
    onCommentClick: () -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp))
                .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (userImageUrl.isNotEmpty()) {
                AsyncImage(
                    model = userImageUrl,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Default Profile",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = username,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Text(
                    text = timeAgo,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = threadTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = threadBody,
            fontSize = 14.sp,
            color = Color.DarkGray,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 20.sp,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Surface(
                color = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = movieTag,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1565C0),
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Likes",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = likesCount.toString(), fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.width(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onCommentClick() },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = "Comments",
                        modifier = Modifier.size(18.dp),
                        tint = Color.Gray,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = commentsCount.toString(), fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }

    HorizontalDivider(thickness = 1.dp, color = Color(0xFFF0F0F0))
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(showBackground = true)
fun ThreadItemPreview() {
    ThreadItem()
}
