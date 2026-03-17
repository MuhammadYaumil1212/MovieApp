package yr.muhammadyaumil.movieapp.ui.register.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.core.composables.AppButton
import yr.muhammadyaumil.movieapp.core.composables.AppScaffold
import yr.muhammadyaumil.movieapp.core.composables.AppTextfield
import yr.muhammadyaumil.movieapp.core.composables.SocialAuthButton
import yr.muhammadyaumil.movieapp.ui.login.screens.OrDivider
import yr.muhammadyaumil.movieapp.ui.register.event.RegisterEvent
import yr.muhammadyaumil.movieapp.ui.register.state.RegisterState

@Suppress("ktlint:standard:function-naming")
@Composable
fun RegisterScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    navigateToLogin: () -> Unit,
    onEvent: (onEvent: RegisterEvent) -> Unit,
    state: RegisterState,
) {
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navigateToLogin()
            onEvent(RegisterEvent.ResetSucessState)
        }
    }
    AppScaffold(
        modifier = modifier,
        isLoading = false,
        errorMessage = state.errorMessage,
        showErrorTextCenter = state.errorMessage != null,
        onErrorConsumed = { onEvent(RegisterEvent.DismissError) },
        topBar = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = "Register to continue",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W700,
            )
            Spacer(modifier = Modifier.height(20.dp))
            with(state) {
                FormTextfield(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    emailState = this.email,
                    passwordState = this.password,
                    usernameState = this.username,
                    confirmPasswordState = this.confirmPassword,
                    isRegisterLoading = this.isLoading,
                    onRegisterClick = { onEvent(RegisterEvent.SubmitRegistration) },
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OrDivider(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(20.dp))
            OtherRegisterMethod(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.weight(2f))
            LoginNavigation {
                onBackClick()
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun OtherRegisterMethod(modifier: Modifier) {
    Column(modifier = modifier) {
        SocialAuthButton(
            onClick = { },
            text = "Register with Google",
            icon = painterResource(R.drawable.ic_google),
        )
        Spacer(modifier = Modifier.height(20.dp))
        SocialAuthButton(
            onClick = { },
            text = "Register with Apple",
            icon = painterResource(R.drawable.ic_apple),
        )
        Spacer(modifier = Modifier.height(20.dp))
        SocialAuthButton(
            onClick = { },
            text = "Register with Facebook",
            icon = painterResource(R.drawable.ic_facebook),
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
