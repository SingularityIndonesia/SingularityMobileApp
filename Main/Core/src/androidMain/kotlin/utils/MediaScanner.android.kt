package utils

import android.content.Context
import android.media.MediaScannerConnection
import androidx.core.net.toFile
import androidx.core.net.toUri
import java.io.File

object MediaScannerContextProvider {
    private var applicationContext: Context? = null

    fun initialize(context: Context) {
        applicationContext = context.applicationContext
    }

    fun getContext(): Context {
        return applicationContext ?: throw IllegalStateException(
            "ContextProvider not initialized. Call ContextProvider.initialize(context) in your Application class."
        )
    }
}

actual fun notifyMediaScanner(uri: String, onScanCompleted: (String) -> Unit) {
    notifyMediaScanner(
        MediaScannerContextProvider.getContext(),
        uri.toUri().toFile(),
        onScanCompleted
    )
}

fun notifyMediaScanner(
    context: Context,
    file: File,
    onScanCompleted: (path: String) -> Unit = { _ -> }
) {
    MediaScannerConnection.scanFile(
        context,
        arrayOf(file.absolutePath),
        arrayOf("image/jpeg"),
    ) { path: String, uri: android.net.Uri ->
        onScanCompleted.invoke(path)
    }
}