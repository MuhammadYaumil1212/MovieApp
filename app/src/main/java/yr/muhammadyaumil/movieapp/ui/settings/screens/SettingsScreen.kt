package yr.muhammadyaumil.movieapp.ui.settings.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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
    state: SettingsState,
) {
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
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState()),
        ) {
            ProfileHeader(
                state = state,
                navigateToLogin = { navigateToLogin() },
                navigateToProfile = { navigateToProfile() },
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Account",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
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

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileHeader(
    state: SettingsState,
    navigateToLogin: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
            if (state.profileUrl.isNotEmpty()) {
                AsyncImage(
                    model = state.profileUrl,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp)),
                )
            } else {
                Icon(
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.secondary,
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile Icon",
                )
            }
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
}
