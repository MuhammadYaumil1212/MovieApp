package yr.muhammadyaumil.movieapp.core.utils

import java.util.Locale

/**
 * Mengubah rating Double menjadi String dengan 1 digit di belakang koma.
 * Contoh: 8.12345 -> 8.1
 */
fun Double?.formatRating(): String = String.format(Locale.ENGLISH, "%.1f", this ?: 0.0)
