package yr.muhammadyaumil.movieapp.ui.home.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.core.composables.AppEmptyState

@Composable
fun MovieNotFound(
    modifier: Modifier = Modifier,
    onRefreshClick: (() -> Unit)? = null,
    msg: String?,
) {
    AppEmptyState(
        modifier = modifier,
        imageRes = R.drawable.notfound,
        title = "Movie Not Found",
        description = msg,
        actionText = "Muat Ulang",
        onActionClick = onRefreshClick,
    )
}
