package utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Photos.PHAssetChangeRequest
import platform.Photos.PHPhotoLibrary
import platform.UIKit.UIImage
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class, DelicateCoroutinesApi::class)
actual fun notifyMediaScanner(uri: String, onScanCompleted: (path: String) -> Unit) {
    // On iOS, we need to add the image to the photo library for it to be visible in Photos app
    // This is different from Android's MediaScanner which just scans existing files
    
    val nsUrl = NSURL.fileURLWithPath(uri)
    val fileManager = NSFileManager.defaultManager
    
    // Check if file exists
    if (!fileManager.fileExistsAtPath(uri)) {
        // File doesn't exist, just call completion with the original path
        onScanCompleted(uri)
        return
    }
    
    // Launch coroutine to handle async photo library operations
    GlobalScope.launch {
        try {
            val success = saveImageToPhotoLibrary(nsUrl)
            if (success) {
                onScanCompleted(uri)
            } else {
                // Even if saving failed, call completion with original path
                onScanCompleted(uri)
            }
        } catch (e: Exception) {
            // On error, still call completion
            onScanCompleted(uri)
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private suspend fun saveImageToPhotoLibrary(fileUrl: NSURL): Boolean {
    return suspendCancellableCoroutine { continuation ->
        PHPhotoLibrary.sharedPhotoLibrary().performChanges(
            changeBlock = {
                // Create a change request to add the image to photo library
                PHAssetChangeRequest.creationRequestForAssetFromImageAtFileURL(fileUrl)
            },
            completionHandler = { success, error ->
                continuation.resume(success)
            }
        )
    }
}

// Alternative implementation for when you have image data instead of file path
@OptIn(ExperimentalForeignApi::class)
fun notifyMediaScannerWithImage(
    image: UIImage,
    onScanCompleted: (success: Boolean) -> Unit = { _ -> }
) {
    PHPhotoLibrary.sharedPhotoLibrary().performChanges(
        changeBlock = {
            PHAssetChangeRequest.creationRequestForAssetFromImage(image)
        },
        completionHandler = { success, error ->
            onScanCompleted(success)
        }
    )
}

// Utility function to check if we have photo library permission
@OptIn(ExperimentalForeignApi::class)
fun hasPhotoLibraryPermission(): Boolean {
    val status = PHPhotoLibrary.authorizationStatus()
    return status == platform.Photos.PHAuthorizationStatusAuthorized || 
           status == platform.Photos.PHAuthorizationStatusLimited
}
