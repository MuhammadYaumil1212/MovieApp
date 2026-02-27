package yr.muhammadyaumil.movieapp.core.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    showErrorTextCenter: Boolean = false,
    onErrorConsumed: () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    LaunchedEffect(errorMessage) {
        errorMessage?.let { msg ->
            AppSnackbarController.sendEvent(SnackbarEvent(message = msg))
            onErrorConsumed()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .padding(innerPadding),
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                errorMessage != null && showErrorTextCenter -> {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                else -> {
                    content()
                }
            }
        }
    }
}
