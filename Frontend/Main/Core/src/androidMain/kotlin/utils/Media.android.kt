package utils

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

private var mediaPickerLauncher: ActivityResultLauncher<Intent>? = null

private var currentOnMediaSelected: ((List<String>) -> Unit)? = null
private var currentOnCancelled: (() -> Unit)? = null

actual fun launchMediaPicker(
    onMediaSelected: (List<String>) -> Unit,
    onCancelled: () -> Unit
) {
    val launcher = mediaPickerLauncher
    if (launcher == null) {
        onCancelled()
        return
    }

    currentOnMediaSelected = onMediaSelected
    currentOnCancelled = onCancelled

    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        addCategory(Intent.CATEGORY_OPENABLE)
    }

    try {
        launcher.launch(Intent.createChooser(intent, "Select Media"))
    } catch (e: Exception) {
        onCancelled()
        currentOnMediaSelected = null
        currentOnCancelled = null
    }
}

actual fun isMediaPickerAvailable(): Boolean {
    return mediaPickerLauncher != null
}

private fun handleActivityResult(resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK && data != null) {
        val uris = mutableListOf<String>()
        
        if (data.clipData != null) {
            // Multiple files selected
            val clipData = data.clipData!!
            for (i in 0 until clipData.itemCount) {
                val uri = clipData.getItemAt(i).uri
                uris.add(uri.toString())
            }
        } else if (data.data != null) {
            // Single file selected
            uris.add(data.data.toString())
        }
        
        currentOnMediaSelected?.invoke(uris)
    } else {
        currentOnCancelled?.invoke()
    }
    
    // Clear callbacks
    currentOnMediaSelected = null
    currentOnCancelled = null
}
