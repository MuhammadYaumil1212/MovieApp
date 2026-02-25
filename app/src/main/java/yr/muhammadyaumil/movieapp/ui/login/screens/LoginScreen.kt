package yr.muhammadyaumil.movieapp.ui.login.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.core.composables.AppButton
import yr.muhammadyaumil.movieapp.core.composables.AppSnackbarController
import yr.muhammadyaumil.movieapp.core.composables.AppTextfield
import yr.muhammadyaumil.movieapp.core.composables.SnackbarEvent
import yr.muhammadyaumil.movieapp.core.composables.SocialAuthButton
import yr.muhammadyaumil.movieapp.ui.login.event.LoginEvent
import yr.muhammadyaumil.movieapp.ui.login.state.LoginState

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    onBackClick: () -> Unit,
    onEvent: (onEvent: LoginEvent) -> Unit,
    state: StateFlow<LoginState>,
) {
    val uiState by state.collectAsState()
    Scaffold(
        topBar = {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier =
                    Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onBackClick)
                        .padding(16.dp),
            )
        },
    ) { innerPadding ->
        LaunchedEffect(state.collectAsState().value.errorMessage) {
            state.value.errorMessage?.let { msg ->
                AppSnackbarController.sendEvent(SnackbarEvent(message = msg))
                onEvent(LoginEvent.ErrorConsumed)
            }
        }
        LaunchedEffect(uiState.isSuccess) {
            if (uiState.isSuccess) {
                navigateToHome()
                onEvent(LoginEvent.LoginNavigate)
            }
        }
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Text("Login to continue", fontSize = 30.sp, fontWeight = FontWeight.W700)
                Spacer(modifier = Modifier.height(20.dp))
                with(uiState) {
                    FormTextfield(
                        emailState = email,
                        passwordState = password,
                        isLoginLoading = isLoading,
                        onForgotPasswordClick = {},
                        onLoginClick = {
                            onEvent(LoginEvent.LoginClicked)
                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OrDivider()
                Spacer(modifier = Modifier.height(20.dp))
                OtherLoginMethod()
                Spacer(modifier = Modifier.weight(1f))
                RegisterNavigation {
                    navigateToRegister()
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RegisterNavigation(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Haven't register yet?",
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
            text = "Register Here",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun OtherLoginMethod() {
    Column {
        SocialAuthButton(
            onClick = { },
            text = "Login with Google",
            icon = painterResource(R.drawable.ic_google),
        )
        Spacer(modifier = Modifier.height(20.dp))
        SocialAuthButton(
            onClick = { },
            text = "Login with Apple",
            icon = painterResource(R.drawable.ic_apple),
        )
        Spacer(modifier = Modifier.height(20.dp))
        SocialAuthButton(
            onClick = { },
            text = "Login with Facebook",
            icon = painterResource(R.drawable.ic_facebook),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FormTextfield(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoginLoading: Boolean = false,
) {
    Column(modifier = modifier) {
        AppTextfield(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Email,
            textfieldState = emailState,
            hint = "Email",
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
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier.clickable(onClick = onForgotPasswordClick),
                text = "Forgot Password?",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AppButton(
            onClick = onLoginClick,
            isLoading = isLoginLoading,
            text = "Login",
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun OrDivider(
    modifier: Modifier = Modifier,
    text: String = "Or",
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
        )

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        )

        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
        )
    }
}
