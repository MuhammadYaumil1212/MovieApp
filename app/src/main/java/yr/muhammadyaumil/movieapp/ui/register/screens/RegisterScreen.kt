package yr.muhammadyaumil.movieapp.ui.register.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import yr.muhammadyaumil.movieapp.core.composables.AppButton
import yr.muhammadyaumil.movieapp.core.composables.AppSnackbarController
import yr.muhammadyaumil.movieapp.core.composables.AppTextfield
import yr.muhammadyaumil.movieapp.core.composables.SnackbarEvent
import yr.muhammadyaumil.movieapp.ui.register.event.RegisterEvent
import yr.muhammadyaumil.movieapp.ui.register.state.RegisterState

@Suppress("ktlint:standard:function-naming")
@Composable
fun RegisterScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onEvent: (onEvent: RegisterEvent) -> Unit,
    state: StateFlow<RegisterState>,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Scaffold(
        topBar = {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier =
                    Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onBackClick)
                        .padding(16.dp)
                        .indication(
                            interactionSource = interactionSource,
                            indication = null,
                        ),
            )
        },
    ) { innerPadding ->
        LaunchedEffect(state.collectAsState().value.errorMessage) {
            state.value.errorMessage?.let { msg ->
                AppSnackbarController.sendEvent(SnackbarEvent(message = msg))
                onEvent(RegisterEvent.ErrorConsumed)
            }
        }
        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding),
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                androidx.compose.material.Text(
                    "Register to continue",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W700,
                )
                Spacer(modifier = Modifier.height(20.dp))
                with(state.collectAsState()) {
                    FormTextfield(
                        emailState = this.value.email,
                        passwordState = this.value.password,
                        usernameState = this.value.username,
                        confirmPasswordState = this.value.confirmPassword,
                        isRegisterLoading = this.value.isLoading,
                        phoneNumberState = this.value.phoneNumber,
                        onRegisterClick = { onEvent(RegisterEvent.RegisterClicked) },
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                LoginNavigation {
                    onBackClick()
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginNavigation(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Have been register?",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.width(5.dp))
        Text(
            modifier =
                Modifier
                    .clip(CircleShape)
                    .clickable(onClick = onClick),
            text = "Login Here",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FormTextfield(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    usernameState: TextFieldState,
    phoneNumberState: TextFieldState,
    confirmPasswordState: TextFieldState,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    isRegisterLoading: Boolean = false,
) {
    Column(modifier = modifier) {
        AppTextfield(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Email,
            textfieldState = emailState,
            hint = "Email",
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppTextfield(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.PersonOutline,
            textfieldState = usernameState,
            hint = "Username",
        )
        Spacer(modifier = Modifier.height(20.dp))

        AppTextfield(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.PersonOutline,
            textfieldState = phoneNumberState,
            keyboardType = KeyboardType.Number,
            hint = "Phone Number",
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.End) {
            AppTextfield(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Outlined.Password,
                textfieldState = passwordState,
                isPassword = true,
                hint = "Password",
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextfield(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Outlined.Password,
                textfieldState = confirmPasswordState,
                isPassword = true,
                hint = "Confirm Password",
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AppButton(
            onClick = onRegisterClick,
            text = "Register",
            isLoading = isRegisterLoading,
        )
    }
}
