package yr.muhammadyaumil.movieapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object Home : Screen("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)

    object DetailHome :
        Screen("detailHome/{movieId}", "Detail Home", Icons.Filled.Home, Icons.Outlined.Home) {
        fun createRoute(movieId: Int) = "detailHome/$movieId"
    }

    object Settings : Screen("settings", "Settings", Icons.Filled.Settings, Icons.Outlined.Settings)

    object Login : Screen("login", "login", Icons.Filled.Person, Icons.Outlined.Person)

    object Register : Screen("register", "register", Icons.Filled.Person, Icons.Outlined.Person)

    object Profile : Screen("profile", "profile", Icons.Filled.Person, Icons.Outlined.Person)

    object Community : Screen("community", "community", Icons.Filled.Groups, Icons.Outlined.Groups)

    object WatchList :
        Screen("watchList", "Watch List", Icons.Filled.PlayArrow, Icons.Outlined.PlayArrow)

    object Favorite :
        Screen("favorite", "Favorite", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)

    companion object {
        val bottomNavItems = listOf(Home, Community, Settings)
    }
}
