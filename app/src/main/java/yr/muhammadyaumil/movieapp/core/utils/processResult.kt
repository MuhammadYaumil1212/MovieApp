package yr.muhammadyaumil.movieapp.core.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import yr.muhammadyaumil.movieapp.core.state.Resources

/**
 * Extension function reusable untuk memproses Resources<T> ke dalam StateFlow.
 *
 * @param T: Tipe data dari Resources (misal: Unit, List<Movie>, User)
 * @param S: Tipe data dari State (misal: SignInState, HomeState)
 */
fun <T, S> MutableStateFlow<S>.handleResource(
    resource: Resources<T>,
    onLoading: (S) -> S,
    onSuccess: (S, T?) -> S,
    onError: (S, String?) -> S,
) {
    this.update { currentState ->
        when (resource) {
            is Resources.Loading -> onLoading(currentState)
            is Resources.Success -> onSuccess(currentState, resource.data)
            is Resources.Error -> onError(currentState, resource.message)
        }
    }
}
