package yr.muhammadyaumil.movieapp.ui.profile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.ui.profile.event.ProfileEvent
import yr.muhammadyaumil.movieapp.ui.profile.state.ProfileState

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreen(
    modifier: Modifier,
    navigateBack: () -> Unit,
    onEvent: (onEvent: ProfileEvent) -> Unit,
    state: ProfileState,
) {
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onEvent(ProfileEvent.ResetSucessState)
        }
    }
    AppScaffold(
        modifier = modifier,
        isLoading = false,
        errorMessage = state.errorMessage,
        showErrorTextCenter = state.errorMessage != null,
        onErrorConsumed = { onEvent(ProfileEvent.DismissError) },
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
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                ImageProfileEdit()
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                ProfileInfoCard()
                Spacer(modifier = Modifier.height(20.dp))
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
fun ProfileInfoCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            InfoRowItem(
                label = "Name",
                value = "John Doe",
                onClick = { /* Handle click event */ },
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.3f),
            )
            InfoRowItem(
                label = "Email",
                value = "email@email.com",
                onClick = { /* Handle click event */ },
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.3f),
            )
            InfoRowItem(
                label = "Phone Number",
                value = "081267197393",
                onClick = { /* Handle click event */ },
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.3f),
            )
            InfoRowItem(
                label = "Change Password",
                value = "*******",
                onClick = { /* Handle click event */ },
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun InfoRowItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.DarkGray,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = "Navigate to $label",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}
