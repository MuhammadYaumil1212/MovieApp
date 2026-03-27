package yr.muhammadyaumil.movieapp.ui.community.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.core.composables.AppEmptyState

@Composable
fun ThreadNotFound(
    modifier: Modifier = Modifier,
    onRefreshClick: (() -> Unit)? = null,
) {
    AppEmptyState(
        modifier = modifier,
        imageRes = R.drawable.notfound,
        title = "Discussions Not Found",
        description = "Belum ada diskusi Film. Jadilah yang pertama memulai obrolan!",
        actionText = "Muat Ulang",
        onActionClick = onRefreshClick,
    )
}
