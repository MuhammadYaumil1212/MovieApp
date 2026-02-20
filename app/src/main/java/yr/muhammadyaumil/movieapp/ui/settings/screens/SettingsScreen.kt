package yr.muhammadyaumil.movieapp.ui.settings.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import yr.muhammadyaumil.movieapp.ui.settings.composables.SettingsItem

data class SettingsItem(
    val icon: ImageVector? = null,
    val title: String,
)

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val listItemAkun =
        listOf(
            SettingsItem(icon = Icons.Outlined.Person, title = "Edit Profile"),
            SettingsItem(icon = Icons.Outlined.ConfirmationNumber, title = "My Ticket"),
            SettingsItem(icon = Icons.Outlined.Link, title = "Linked Account"),
        )

    val listItemFilm =
        listOf(
            SettingsItem(icon = Icons.Outlined.Movie, title = "My Films"),
            SettingsItem(icon = Icons.Outlined.VideoLibrary, title = "Watchlist"),
            SettingsItem(icon = Icons.Outlined.FavoriteBorder, title = "Favorite"),
        )
    Column {
        Box(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(8.dp),
                        ).padding(vertical = 8.dp),
            ) {
//                AsyncImage(
//                    model = "https://image.tmdb.org/t/p/w200/uje1ecKMnNpZp0at5TxlvVgVXqI.jpg",
//                    contentDescription = null,
//                    modifier =
//                        Modifier
//                            .padding(horizontal = 10.dp)
//                            .size(60.dp)
//                            .clip(RoundedCornerShape(10.dp)),
//                    contentScale = ContentScale.Crop,
//                )
                Icon(
                    Icons.Outlined.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier =
                        Modifier
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp)),
                )
                Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                    Text(
                        "Login or Register Account",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "Login or register with a registered account",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
        Text(
            text = "Account",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp),
        )
        Box(modifier = modifier.padding(16.dp)) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(8.dp),
                        ).padding(vertical = 8.dp),
            ) {
                listItemAkun.forEach { item ->
                    SettingsItem(
                        leadingIcon = item.icon,
                        title = item.title,
                    )
                }
            }
        }
        Text(
            text = "Film",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp),
        )
        Box(modifier = modifier.padding(16.dp)) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(8.dp),
                        ).padding(vertical = 8.dp),
            ) {
                listItemFilm.forEach { item ->
                    SettingsItem(
                        leadingIcon = item.icon,
                        title = item.title,
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview() {
    AppTheme {
        SettingsScreen()
    }
}
