package yr.muhammadyaumil.movieapp.ui.settings.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.StateFlow
import yr.muhammadyaumil.movieapp.core.composables.AppButton
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.ui.settings.composables.SettingsItem
import yr.muhammadyaumil.movieapp.ui.settings.event.SettingsEvent
import yr.muhammadyaumil.movieapp.ui.settings.state.SettingsState

data class SettingsMenuOption(
    val icon: ImageVector? = null,
    val title: String,
)

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToProfile: () -> Unit,
    onEvent: (onEvent: SettingsEvent) -> Unit,
    state: StateFlow<SettingsState>,
) {
    val state by state.collectAsState()

    val listItemAkun =
        remember {
            listOf(
                SettingsMenuOption(icon = Icons.Outlined.Person, title = "Edit Profile"),
                SettingsMenuOption(icon = Icons.Outlined.ConfirmationNumber, title = "My Ticket"),
                SettingsMenuOption(icon = Icons.Outlined.Link, title = "Linked Account"),
            )
        }

    val listItemFilm =
        remember {
            listOf(
                SettingsMenuOption(icon = Icons.Outlined.Movie, title = "My Films"),
                SettingsMenuOption(icon = Icons.Outlined.VideoLibrary, title = "Watchlist"),
                SettingsMenuOption(icon = Icons.Outlined.FavoriteBorder, title = "Favorite"),
            )
        }

    AppScaffold(
        modifier = modifier,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        showErrorTextCenter = !state.isAuthenticated,
        onErrorConsumed = { onEvent(SettingsEvent.ErrorConsumed) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(8.dp),
                        ).clickable {
                            if (!state.isAuthenticated) navigateToLogin() else navigateToProfile()
                        }.padding(16.dp),
            ) {
                if (!state.isAuthenticated) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "Profile Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier =
                            Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(8.dp)),
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column {
                        Text(
                            text = "Login or Register Account",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Login or register with a registered account",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                } else {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w200/uje1ecKMnNpZp0at5TxlvVgVXqI.jpg",
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier =
                            Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(8.dp)),
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column {
                        Text(
                            text = state.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = state.phoneNumber,
                            style = MaterialTheme.typography.bodySmall,
                            color =
                                if (!state.phoneNumber.contains("Please verify")) {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                } else {
                                    MaterialTheme.colorScheme.error
                                },
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Account",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(8.dp),
                        ).padding(vertical = 4.dp),
            ) {
                listItemAkun.forEach { item ->
                    SettingsItem(
                        leadingIcon = item.icon,
                        title = item.title,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Film",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(8.dp),
                        ).padding(vertical = 4.dp),
            ) {
                listItemFilm.forEach { item ->
                    SettingsItem(
                        leadingIcon = item.icon,
                        title = item.title,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (state.isAuthenticated) {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEvent(SettingsEvent.onLogout) },
                    text = "Logout",
                    isLoading = state.logoutLoading,
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Version 1.0 (Comedy)",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
