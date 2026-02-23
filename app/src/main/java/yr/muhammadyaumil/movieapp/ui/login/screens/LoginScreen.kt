package yr.muhammadyaumil.movieapp.ui.login.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.ui.login.composables.AppTextfield

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
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
                        .clickable(onClick = onClick)
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
                Text("Login to continue", fontSize = 20.sp, fontWeight = FontWeight.W700)
                Spacer(modifier = Modifier.height(20.dp))
                AppTextfield(
                    modifier = modifier,
                    leadingIcon = Icons.Outlined.Email,
                    textfieldState = emailState,
                    hint = "Email",
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(horizontalAlignment = Alignment.End) {
                    AppTextfield(
                        modifier = modifier,
                        leadingIcon = Icons.Outlined.Password,
                        textfieldState = passwordState,
                        isPassword = true,
                        hint = "Password",
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        modifier = Modifier.clickable(onClick = {}),
                        text = "Lupa password?",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.W500,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                ElevatedButton(
                    onClick = {
                        // TODO: Handle click
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    enabled = true,
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                    elevation =
                        ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 6.dp,
                        ),
                    contentPadding = PaddingValues(vertical = 14.dp),
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OrDivider()
                Spacer(modifier = Modifier.height(20.dp))
                ElevatedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                    elevation =
                        ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 6.dp,
                        ),
                    contentPadding = PaddingValues(vertical = 14.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_google),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Login with Google",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
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
