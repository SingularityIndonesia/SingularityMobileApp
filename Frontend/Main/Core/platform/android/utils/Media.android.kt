@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package utils

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.invoke

actual object MediaPicker {
    private var mediaPickerLauncher: ActivityResultLauncher<Intent>? = null

    // launch this on activity created
    fun setupMediaPickerLauncher(activity: ComponentActivity) {
        mediaPickerLauncher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            handleActivityResult(result.resultCode, result.data)
        }
    }

    private var currentOnMediaSelected: ((List<String>) -> Unit)? = null
    private var currentOnCancelled: (() -> Unit)? = null

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
            Intent.setType = "image/*"
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
}
