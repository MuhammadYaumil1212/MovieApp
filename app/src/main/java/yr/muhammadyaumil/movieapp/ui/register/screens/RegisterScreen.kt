package yr.muhammadyaumil.movieapp.ui.register.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("ktlint:standard:function-naming")
@Composable
fun RegisterScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            Text("Register page")
        }
    }
}
