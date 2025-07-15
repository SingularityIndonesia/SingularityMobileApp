package utils

import kotlinx.cinterop.ExperimentalForeignApi
import platform.PhotosUI.*
import platform.UIKit.UIApplication
import platform.darwin.NSObject

private var currentOnMediaSelected: ((List<String>) -> Unit)? = null
private var currentOnCancelled: (() -> Unit)? = null

@OptIn(ExperimentalForeignApi::class)
actual fun launchMediaPicker(
    onMediaSelected: (List<String>) -> Unit,
    onCancelled: () -> Unit
) {
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    if (rootViewController == null) {
        onCancelled()
        return
    }

    currentOnMediaSelected = onMediaSelected
    currentOnCancelled = onCancelled

    val configuration = PHPickerConfiguration().apply {
        selectionLimit = 0 // 0 means no limit
        filter = PHPickerFilter.imagesFilter // Only images for now
    }

    val picker = PHPickerViewController(configuration)
    picker.delegate = object : NSObject(), PHPickerViewControllerDelegateProtocol {
        override fun picker(picker: PHPickerViewController, didFinishPicking: List<*>) {
            picker.dismissViewControllerAnimated(true, null)
            
            val results = didFinishPicking.filterIsInstance<PHPickerResult>()
            if (results.isEmpty()) {
                currentOnCancelled?.invoke()
                currentOnMediaSelected = null
                currentOnCancelled = null
                return
            }
            
            // For now, let's just use the first selected item as a placeholder
            // In a real implementation, you'd need to properly handle the PHPickerResult
            // to get the actual file URLs or data
            val uris = results.mapNotNull { result ->
                // This is a simplified implementation
                // You would typically need to load the actual data from the result
                "placeholder_uri_${results.indexOf(result)}"
            }
            
            currentOnMediaSelected?.invoke(uris)
            currentOnMediaSelected = null
            currentOnCancelled = null
        }
    }

    rootViewController.presentViewController(picker, animated = true, completion = null)
}

actual fun isMediaPickerAvailable(): Boolean {
    return UIApplication.sharedApplication.keyWindow?.rootViewController != null
}
