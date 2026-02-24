package yr.muhammadyaumil.movieapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import yr.muhammadyaumil.movieapp.core.composables.AppSnackbarController
import yr.muhammadyaumil.movieapp.core.composables.MovieBottomBar
import yr.muhammadyaumil.movieapp.core.composables.ObserveAsEvents
import yr.muhammadyaumil.movieapp.core.navigation.Screen
import yr.muhammadyaumil.movieapp.ui.home.screens.HomeScreen
import yr.muhammadyaumil.movieapp.ui.login.screens.LoginScreen
import yr.muhammadyaumil.movieapp.ui.login.viewmodel.LoginViewModel
import yr.muhammadyaumil.movieapp.ui.register.screens.RegisterScreen
import yr.muhammadyaumil.movieapp.ui.register.viewmodel.RegisterViewModel
import yr.muhammadyaumil.movieapp.ui.settings.screens.SettingsScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val bottomNavRoutes = Screen.bottomNavItems.map { it.route }
    val showBottomBar = bottomNavRoutes.contains(currentRoute?.route)
    val snackbarHostState =
        remember {
            SnackbarHostState()
        }
    val scope = rememberCoroutineScope()
    ObserveAsEvents(
        flow = AppSnackbarController.events,
        snackbarHostState,
    ) { events ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val snackResult =
                snackbarHostState.showSnackbar(
                    message = events.message,
                    actionLabel = events.action?.name,
                    duration = SnackbarDuration.Short,
                )
            if (snackResult == SnackbarResult.ActionPerformed) {
                events.action?.action?.invoke()
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
                val loginViewModel = hiltViewModel<LoginViewModel>()
                val state = loginViewModel.state
                LoginScreen(
                    modifier = Modifier,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    onEvent = loginViewModel::onEvent,
                    state = state,
                )
            }
            composable(Screen.Register.route) {
                val registerViewModel = hiltViewModel<RegisterViewModel>()
                val state = registerViewModel.state
                RegisterScreen(
                    modifier = Modifier,
                    onEvent = registerViewModel::onEvent,
                    state = state,
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
