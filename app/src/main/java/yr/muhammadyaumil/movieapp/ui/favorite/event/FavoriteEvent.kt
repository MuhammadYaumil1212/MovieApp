package yr.muhammadyaumil.movieapp.ui.favorite.event

import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity

sealed interface FavoriteEvent {
    data class AddFavorite(
        val movieData: FavoriteEntity,
    ) : FavoriteEvent

    data class DeleteFavorite(
        val movieData: FavoriteEntity,
    ) : FavoriteEvent

    data object DismissError : FavoriteEvent
}
