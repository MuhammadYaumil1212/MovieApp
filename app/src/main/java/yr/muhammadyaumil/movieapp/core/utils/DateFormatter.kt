package yr.muhammadyaumil.movieapp.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Mengubah format tanggal dari API TMDB yyyy-MM-dd ke format dd / MMM/ yyyy.
 * Contoh: 2024-02-20 -> 20 Feb 2024
 */
fun String?.formatDate(): String {
    if (this.isNullOrEmpty()) return "Unknown Date"
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd / MMM / yyyy", Locale.getDefault())
        val date = parser.parse(this)
        date?.let { formatter.format(it) } ?: this
    } catch (e: Exception) {
        this
    }
}
