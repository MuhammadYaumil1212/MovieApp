package yr.muhammadyaumil.movieapp.ui.settings.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import yr.muhammadyaumil.movieapp.ui.settings.composables.SettingsItem

data class SettingsItem(
    val icon: ImageVector? = null,
    val title: String,
)

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val listItem =
        listOf(
            SettingsItem(icon = Icons.Filled.Person, title = "Profile"),
            SettingsItem(icon = Icons.Filled.Movie, title = "My Films"),
            SettingsItem(icon = Icons.Filled.VideoLibrary, title = "Watchlist"),
        )
    LazyColumn(verticalArrangement = Arrangement.Center) {
        items(listItem) { item ->
            SettingsItem(
                leadingIcon = item.icon,
                title = item.title,
            )
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
