package yr.muhammadyaumil.movieapp.ui.login.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.composables.AppTextfield
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
    val state by state.collectAsState()
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navigateToHome()
            onEvent(LoginEvent.ResetSuccessState)
        }
    }
    AppScaffold(
        modifier = modifier,
        isLoading = false,
        errorMessage = state.errorMessage,
        showErrorTextCenter = state.errorMessage != null,
        onErrorConsumed = { onEvent(LoginEvent.DismissError) },
        topBar = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                )
            }
        },
    ) {
        Column {
            Text(
                text = "Login to continue",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W700,
            )
            Spacer(modifier = Modifier.height(20.dp))
            with(state) {
                FormTextfield(
                    emailState = email,
                    passwordState = password,
                    isLoginLoading = isLoading,
                    onForgotPasswordClick = {},
                    onLoginClick = {
                        onEvent(LoginEvent.SubmitLogin)
                    },
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(20.dp))
            OtherLoginMethod()
            Spacer(modifier = Modifier.weight(2f))
            RegisterNavigation {
                navigateToRegister()
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
            text = "Not registered yet ?",
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
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = DividerDefaults.color,
        )

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = DividerDefaults.color,
        )
    }
}
