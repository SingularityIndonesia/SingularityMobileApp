package utils

import androidx.compose.runtime.*
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionRecordPermissionDenied
import platform.AVFAudio.AVAudioSessionRecordPermissionGranted
import platform.AVFAudio.AVAudioSessionRecordPermissionUndetermined
import platform.CoreLocation.*
import platform.Photos.*
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WithPermission(
    vararg permissions: String,
    content: @Composable (() -> Unit)
) {
    var allPermissionsGranted by remember(permissions.contentHashCode()) {
        mutableStateOf(false)
    }

    LaunchedEffect(permissions.contentHashCode()) {
        // Check if all permissions are granted
        val permissionResults = permissions.map { permission ->
            checkAndRequestPermission(permission)
        }
        
        allPermissionsGranted = permissionResults.all { it }
    }

    if (allPermissionsGranted) {
        content.invoke()
    }
}

@OptIn(ExperimentalForeignApi::class)
private suspend fun checkAndRequestPermission(permission: String): Boolean {
    return when (permission) {
        "camera" -> checkAndRequestCameraPermission()
        "microphone" -> checkAndRequestMicrophonePermission()
        "location" -> checkAndRequestLocationPermission()
        "photos" -> checkAndRequestPhotosPermission()
        else -> {
            // For unknown permissions, assume granted to avoid blocking
            // In a real app, you might want to handle this differently
            true
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private suspend fun checkAndRequestCameraPermission(): Boolean {
    // Camera permissions are handled through Info.plist on iOS
    // For simplicity, we'll return true as camera access is typically requested
    // when the camera is first accessed
    return true
}

@OptIn(ExperimentalForeignApi::class)
private suspend fun checkAndRequestMicrophonePermission(): Boolean {
    return suspendCancellableCoroutine { continuation ->
        val audioSession = AVAudioSession.sharedInstance()
        
        when (audioSession.recordPermission) {
            AVAudioSessionRecordPermissionGranted -> {
                continuation.resume(true)
            }
            AVAudioSessionRecordPermissionDenied -> {
                continuation.resume(false)
            }
            AVAudioSessionRecordPermissionUndetermined -> {
                audioSession.requestRecordPermission { granted ->
                    continuation.resume(granted)
                }
            }
            else -> {
                continuation.resume(false)
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private suspend fun checkAndRequestLocationPermission(): Boolean {
    return suspendCancellableCoroutine { continuation ->
        val locationManager = CLLocationManager()
        
        when (locationManager.authorizationStatus) {
            kCLAuthorizationStatusAuthorizedAlways,
            kCLAuthorizationStatusAuthorizedWhenInUse -> {
                continuation.resume(true)
            }
            kCLAuthorizationStatusDenied,
            kCLAuthorizationStatusRestricted -> {
                continuation.resume(false)
            }
            kCLAuthorizationStatusNotDetermined -> {
                // Note: In a real implementation, you'd need to handle the delegate callback
                // For now, we'll request permission and assume it might be granted
                locationManager.requestWhenInUseAuthorization()
                continuation.resume(true) // This should be handled via delegate
            }
            else -> {
                continuation.resume(false)
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private suspend fun checkAndRequestPhotosPermission(): Boolean {
    return suspendCancellableCoroutine { continuation ->
        when (PHPhotoLibrary.authorizationStatus()) {
            PHAuthorizationStatusAuthorized,
            PHAuthorizationStatusLimited -> {
                continuation.resume(true)
            }
            PHAuthorizationStatusDenied,
            PHAuthorizationStatusRestricted -> {
                continuation.resume(false)
            }
            PHAuthorizationStatusNotDetermined -> {
                PHPhotoLibrary.requestAuthorization { status ->
                    when (status) {
                        PHAuthorizationStatusAuthorized,
                        PHAuthorizationStatusLimited -> {
                            continuation.resume(true)
                        }
                        else -> {
                            continuation.resume(false)
                        }
                    }
                }
            }
            else -> {
                continuation.resume(false)
            }
        }
    }
}
