package yr.muhammadyaumil.movieapp.ui.Community.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import yr.muhammadyaumil.movieapp.R

@Suppress("ktlint:standard:function-naming")
@Composable
fun StoryItem(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    name: String = "",
) {
    if (imageUrl.isNotEmpty()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier =
                    Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(50.dp)),
                model = imageUrl,
                contentDescription = imageUrl,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "No Name",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                fontWeight = FontWeight.W300,
                color = Color.Black,
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier =
                    Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(50.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.placeholder),
                contentDescription = "image placeholder",
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "No Name",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(backgroundColor = 0xFFFAFAFA, showBackground = true)
fun StoryItemPreview() {
    StoryItem()
}
