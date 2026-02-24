package yr.muhammadyaumil.movieapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import yr.muhammadyaumil.movieapp.core.composables.MovieBottomBar
import yr.muhammadyaumil.movieapp.core.navigation.Screen
import yr.muhammadyaumil.movieapp.ui.home.screens.HomeScreen
import yr.muhammadyaumil.movieapp.ui.login.screens.LoginScreen
import yr.muhammadyaumil.movieapp.ui.register.screens.RegisterScreen
import yr.muhammadyaumil.movieapp.ui.settings.screens.SettingsScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val bottomNavRoutes = Screen.bottomNavItems.map { it.route }
    val showBottomBar = bottomNavRoutes.contains(currentRoute?.route)
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter =
                    slideInVertically(
                        initialOffsetY = { it },
                    ),
                exit =
                    slideOutVertically(
                        targetOffsetY = { it },
                    ),
            ) {
                NavigationBar {
                    if (showBottomBar) {
                        MovieBottomBar(
                            navController = navController,
                            items = Screen.bottomNavItems,
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier = Modifier, navigateToLogin = {
                    navController.navigate(Screen.Login.route)
                })
            }
            composable(Screen.Settings.route) {
                SettingsScreen(modifier = Modifier, navigateToLogin = {
                    navController.navigate(Screen.Login.route)
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    modifier = Modifier,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(modifier = Modifier, onBackClick = {
                    navController.popBackStack()
                })
            }
        }
    }
}
