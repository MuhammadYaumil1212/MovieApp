package yr.muhammadyaumil.movieapp.ui.detailHome.event

sealed interface DetailHomeEvent {
    data object ResetError : DetailHomeEvent

    data class ToggleFavorite(
        val movieId: Int,
    ) : DetailHomeEvent
}
