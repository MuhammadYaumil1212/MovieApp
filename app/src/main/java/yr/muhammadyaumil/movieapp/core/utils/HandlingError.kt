package yr.muhammadyaumil.movieapp.core.utils

import io.github.jan.supabase.exceptions.RestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import java.net.UnknownHostException

fun Throwable.toDynamicError(): String =
    when (this) {
        is RestException -> {
            val serverMessage = this.description ?: "Server Error"
            cleanUpMessage(serverMessage)
        }

        is HttpRequestTimeoutException, is UnknownHostException -> {
            "No Internet Connection"
        }

        else -> {
            this.message ?: "Unknown Error occurred"
        }
    }

private fun cleanUpMessage(msg: String): String =
    msg
        .replace("_", " ")
        .replaceFirstChar {
            it.uppercase()
        }
