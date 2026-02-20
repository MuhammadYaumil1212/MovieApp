package yr.muhammadyaumil.movieapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import yr.muhammadyaumil.movieapp.core.composables.MovieBottomBar
import yr.muhammadyaumil.movieapp.core.navigation.Screen
import yr.muhammadyaumil.movieapp.ui.home.screens.HomeScreen
import yr.muhammadyaumil.movieapp.ui.settings.screens.SettingsScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                MovieBottomBar(navController = navController, Screen.bottomNavItems)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) { HomeScreen(modifier = Modifier) }
            composable(Screen.Settings.route) { SettingsScreen(modifier = Modifier) }
        }
    }
}
