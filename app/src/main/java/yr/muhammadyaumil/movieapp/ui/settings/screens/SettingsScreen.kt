package yr.muhammadyaumil.movieapp.ui.settings.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            SettingsItem(icon = Icons.AutoMirrored.Outlined.AirplaneTicket, title = "My Ticket"),
            SettingsItem(icon = Icons.Outlined.Link, title = "Akun Terhubung"),
        )

    val listItemFilm =
        listOf(
            SettingsItem(icon = Icons.Outlined.Movie, title = "My Films"),
            SettingsItem(icon = Icons.Outlined.VideoLibrary, title = "Watchlist"),
            SettingsItem(icon = Icons.Outlined.Favorite, title = "Favorite"),
        )
    Column {
        Text(
            text = "Akun",
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
