package yr.muhammadyaumil.movieapp.ui.signIn.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppTextfield(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textfieldState: TextFieldState,
    hint: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
) {
    if (isPassword) {
        AppPasswordTextfield(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            trailingText = trailingText,
            textfieldState = textfieldState,
            hint = hint,
            keyboardType = keyboardType,
            onLeadingClick = onLeadingClick,
            onTrailingClick = onTrailingClick,
        )
    } else {
        AppBasicTextfield(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            trailingText = trailingText,
            textfieldState = textfieldState,
            hint = hint,
            keyboardType = keyboardType,
            onLeadingClick = onLeadingClick,
            onTrailingClick = onTrailingClick,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppBasicTextfield(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textfieldState: TextFieldState,
    hint: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
) {
    BasicTextField(
        state = textfieldState,
        textStyle =
            LocalTextStyle.current.copy(
                color = MaterialTheme.colors.onBackground,
            ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        modifier = modifier,
        decorator = { innerTextfield ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(0.5f),
                            modifier = Modifier.clickable { onLeadingClick() },
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (textfieldState.text.isEmpty()) {
                            if (hint != null) {
                                Text(
                                    text = hint,
                                    color = MaterialTheme.colors.onSurface.copy(0.4f),
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    }
                    if (trailingIcon != null) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(0.5f),
                            modifier = Modifier.clickable { onTrailingClick() },
                        )
                    } else {
                        if (trailingText != null) {
                            Text(
                                text = trailingText,
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.SemiBold,
                                modifier =
                                    Modifier
                                        .padding(end = 4.dp)
                                        .clickable { onTrailingClick() },
                            )
                        }
                    }
                }
                HorizontalDivider(modifier = Modifier.alpha(0.7f))
            }
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppPasswordTextfield(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textfieldState: TextFieldState,
    hint: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
) {
    BasicSecureTextField(
        state = textfieldState,
        textStyle =
            LocalTextStyle.current.copy(
                color = MaterialTheme.colors.onBackground,
            ),
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textObfuscationMode = TextObfuscationMode.Hidden,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        decorator = { innerTextfield ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(0.5f),
                            modifier = Modifier.clickable { onLeadingClick() },
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (textfieldState.text.isEmpty()) {
                            if (hint != null) {
                                Text(
                                    text = hint,
                                    color = MaterialTheme.colors.onSurface.copy(0.4f),
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    }
                    if (trailingIcon != null) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(0.5f),
                            modifier = Modifier.clickable { onTrailingClick() },
                        )
                    } else {
                        if (trailingText != null) {
                            Text(
                                text = trailingText,
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.SemiBold,
                                modifier =
                                    Modifier
                                        .padding(end = 4.dp)
                                        .clickable { onTrailingClick() },
                            )
                        }
                    }
                }
                HorizontalDivider(modifier = Modifier.alpha(0.7f))
            }
        },
    )
}
