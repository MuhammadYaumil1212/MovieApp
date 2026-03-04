package yr.muhammadyaumil.movieapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Theaters
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

    object WatchParty :
        Screen("watchParty", "watchParty", Icons.Filled.Theaters, Icons.Outlined.Theaters)

    object Community : Screen("community", "community", Icons.Filled.Groups, Icons.Outlined.Groups)

    object WatchList :
        Screen("watchList", "Watch List", Icons.Filled.Bookmark, Icons.Outlined.Bookmark)

    object FilmDetail :
        Screen("filmDetail", "filmDetail", Icons.Filled.Person, Icons.Outlined.Person)

    companion object {
        val bottomNavItems = listOf(Home, WatchList, Community, Settings)
    }
}
