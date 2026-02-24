package yr.muhammadyaumil.movieapp.ui.login.screens

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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.core.composables.AppButton
import yr.muhammadyaumil.movieapp.core.composables.SocialAuthButton
import yr.muhammadyaumil.movieapp.ui.login.composables.AppTextfield

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    onBackClick: () -> Unit,
) {
    val emailState = rememberTextFieldState(initialText = "")
    val passwordState = rememberTextFieldState(initialText = "")
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
                EmailAndPasswordTextfield(
                    emailState = emailState,
                    passwordState = passwordState,
                    onForgotPasswordClick = {
                        // Navigasi ke layar lupa password
                    },
                    onLoginClick = {
                        println("Login dengan ${emailState.text} dan ${passwordState.text}")
                    },
                )
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
            text = "Belum mendaftar ?",
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
            text = "Daftar disini",
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
fun EmailAndPasswordTextfield(
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
                text = "Lupa password?",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AppButton(
            onClick = onLoginClick,
            text = if (isLoginLoading) "Loading..." else "Login",
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
