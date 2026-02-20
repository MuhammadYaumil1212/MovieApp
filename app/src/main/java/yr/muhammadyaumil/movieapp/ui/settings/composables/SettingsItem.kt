package yr.muhammadyaumil.movieapp.ui.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("ktlint:standard:function-naming")
@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector? = Icons.AutoMirrored.Outlined.ArrowForwardIos,
    title: String,
    onClick: () -> Unit = {},
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { onClick() },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp,
                    ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (leadingIcon != null) {
                    Icon(leadingIcon, contentDescription = null)
                }
                Text(text = title, fontWeight = FontWeight.W400, fontSize = 14.sp)
            }
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsItemPreview() {
    SettingsItem(
        leadingIcon = Icons.Outlined.Settings,
        title = "Settings",
        trailingIcon = Icons.AutoMirrored.Outlined.ArrowForwardIos,
        onClick = {
            println("jeloo")
        },
    )
}
