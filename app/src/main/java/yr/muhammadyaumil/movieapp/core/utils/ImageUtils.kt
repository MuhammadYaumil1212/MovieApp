import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.graphics.scale
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

object ImageUtils {
    fun compressImage(
        context: Context,
        uri: Uri,
        maxWidth: Int = 1024,
        quality: Int = 80,
    ): ByteArray {
        val inputStream = context.contentResolver.openInputStream(uri)
        val originalBitmap = BitmapFactory.decodeStream(inputStream) ?: return ByteArray(0)
        inputStream?.close()
        val aspectRatio = originalBitmap.width.toDouble() / originalBitmap.height.toDouble()
        val targetWidth = if (originalBitmap.width > maxWidth) maxWidth else originalBitmap.width
        val targetHeight = (targetWidth / aspectRatio).roundToInt()

        val scaledBitmap = originalBitmap.scale(targetWidth, targetHeight)

        val outputStream = ByteArrayOutputStream()
        val format =
            if (android.os.Build.VERSION.SDK_INT >= 30) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                Bitmap.CompressFormat.WEBP
            }

        scaledBitmap.compress(format, quality, outputStream)

        if (originalBitmap != scaledBitmap) originalBitmap.recycle()
        scaledBitmap.recycle()

        return outputStream.toByteArray()
    }
}
