package yr.muhammadyaumil.movieapp.ui.favorite.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import yr.muhammadyaumil.movieapp.R
import yr.muhammadyaumil.movieapp.core.composables.AppEmptyState

@Composable
fun Notfound(
    modifier: Modifier = Modifier,
    onRefreshClick: (() -> Unit)? = null,
) {
    AppEmptyState(
        modifier = modifier,
        imageRes = R.drawable.notfound,
        title = "Favorite Not Found",
        description = "Belum ada Favorite, tambahkan sekarang!",
        actionText = "Muat Ulang",
        onActionClick = onRefreshClick,
    )
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun NotfoundPreview() {
    Notfound()
}
