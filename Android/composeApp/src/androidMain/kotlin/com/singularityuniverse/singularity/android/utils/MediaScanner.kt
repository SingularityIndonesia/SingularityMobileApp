package com.singularityuniverse.singularity.android.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import java.io.File

fun notifyMediaScanner(
    context: Context,
    file: File,
    onScanCompleted: (path: String, uri: Uri) -> Unit = { _, _ -> }
) {
    MediaScannerConnection.scanFile(
        context,
        arrayOf(file.absolutePath),
        arrayOf("image/jpeg"),
        onScanCompleted
    )
}