package yr.muhammadyaumil.movieapp.ui.favorite.state

import yr.muhammadyaumil.movieapp.domain.entity.Favorite.FavoriteEntity

data class FavoriteState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val getFavoriteMovies: List<FavoriteEntity> = emptyList(),
    val successFavorite: Boolean = false,
)
