package yr.muhammadyaumil.movieapp.ui.profile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            LazyColumn {
                item {
                    ImageProfileEdit()
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ImageProfileEdit() {
    Box(
        modifier = Modifier.size(120.dp),
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_report_image),
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color(0xFF4A5144)),
        )

        Surface(
            shape = CircleShape,
            color = Color.White,
            modifier =
                Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomEnd)
                    .offset(
                        x = 4.dp,
                        y = 4.dp,
                    ).clickable { /*camera edit button*/ },
            shadowElevation = 2.dp,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Icon(
                    imageVector = Icons.Outlined.PhotoCamera,
                    contentDescription = "Change Photo Profile",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FormEditTextfield() {
}
