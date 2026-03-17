package yr.muhammadyaumil.movieapp.ui.profile.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.composables.AppTextfield
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
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        successMessage = state.successMessage,
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
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Card(
                    modifier =
                        modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                ) {
                    with(state.userData) {
                        EditableProfileItem(
                            label = "Name",
                            displayValue =
                                this
                                    ?.userMetadata
                                    ?.get("display_name")
                                    ?.toString()
                                    ?.trim('"')
                                    .takeIf { it != null } ?: "",
                            textfieldState = state.usernameState,
                            isEditing = state.isUsernameEdit,
                            onEditClick = { onEvent(ProfileEvent.EditNameProfile) },
                            onCancelClick = { onEvent(ProfileEvent.ResetUsernameProfile) },
                            onSave = {
                                onEvent(ProfileEvent.UpdateProfile)
                                onEvent(ProfileEvent.ResetUsernameProfile)
                            },
                        )
                        EditableProfileItem(
                            label = "Email",
                            displayValue =
                                this
                                    ?.email
                                    .takeIf { it != null } ?: "",
                            textfieldState = state.emailState,
                            isEditing = state.isEmailEdit,
                            onEditClick = { onEvent(ProfileEvent.EditEmailProfile) },
                            onCancelClick = { onEvent(ProfileEvent.ResetEmailProfile) },
                            onSave = {
                                onEvent(ProfileEvent.UpdateProfile)
                                onEvent(ProfileEvent.ResetEmailProfile)
                            },
                        )
                        EditableProfileItem(
                            label = "Phone Number",
                            displayValue =
                                this
                                    ?.phone
                                    .takeIf { it != null } ?: "",
                            textfieldState = state.phoneState,
                            isEditing = state.isPhoneEdit,
                            onEditClick = { onEvent(ProfileEvent.EditPhoneProfile) },
                            onCancelClick = { onEvent(ProfileEvent.ResetPhoneProfile) },
                            onSave = {
                                onEvent(ProfileEvent.UpdateProfile)
                                onEvent(ProfileEvent.ResetPhoneProfile)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun EditableProfileItem(
    modifier: Modifier = Modifier,
    label: String,
    displayValue: String,
    textfieldState: TextFieldState,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onCancelClick: () -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    onSave: () -> Unit = {},
) {
    if (isEditing) {
        AppTextfield(
            textfieldState = textfieldState,
            hint = "Insert New $label",
            keyboardType = keyboardType,
            disableBorder = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = Icons.Default.Clear,
            onTrailingClick = { onCancelClick() },
            onSave = { onSave() },
        )
    } else {
        InfoRowItem(
            modifier = Modifier.padding(horizontal = 10.dp),
            label = label,
            value = displayValue,
            onClick = { onEditClick() },
        )
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
            fontWeight = FontWeight.W500,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = value.replace(".{10}(?=@)".toRegex(), "..."),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.StartEllipsis,
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
