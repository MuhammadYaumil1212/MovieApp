package yr.muhammadyaumil.movieapp.ui.profile.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import yr.muhammadyaumil.movieapp.ui.profile.event.ProfileEvent
import yr.muhammadyaumil.movieapp.ui.profile.state.ProfileState

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreen(
    modifier: Modifier,
    navigateBack: () -> Unit,
    onEvent: (onEvent: ProfileEvent) -> Unit,
    state: StateFlow<ProfileState>,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                        )
                    }
                },
                windowInsets = WindowInsets(0.dp),
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
        ) {
            LazyColumn {
                item {
                    Text("General Section List")
                }
            }
        }
    }
}
