package yr.muhammadyaumil.movieapp.core.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color? = null,
) {
    ElevatedButton(
        onClick = onClick,
        modifier =
            modifier
                .fillMaxWidth(),
        enabled = true,
        shape = RoundedCornerShape(12.dp),
        border = if (borderColor != null) BorderStroke(1.dp, borderColor) else null,
        colors =
            ButtonDefaults.elevatedButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        elevation =
            ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 6.dp,
            ),
        contentPadding = PaddingValues(vertical = 14.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SocialAuthButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color? = null,
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors =
            ButtonDefaults.elevatedButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        elevation =
            ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 6.dp,
            ),
        contentPadding = PaddingValues(vertical = 14.dp),
        border = if (borderColor != null) BorderStroke(1.dp, borderColor) else null,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
