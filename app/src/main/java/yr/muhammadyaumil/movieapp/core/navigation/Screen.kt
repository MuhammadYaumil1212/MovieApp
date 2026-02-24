package yr.muhammadyaumil.movieapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object Home : Screen("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)

    object Settings : Screen("settings", "Settings", Icons.Filled.Settings, Icons.Outlined.Settings)

    object Login : Screen("login", "login", Icons.Filled.Person, Icons.Outlined.Person)

    object Register : Screen("register", "register", Icons.Filled.Person, Icons.Outlined.Person)

    companion object {
        val bottomNavItems = listOf(Home, Settings)
    }
}
