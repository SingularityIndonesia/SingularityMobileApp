package utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.core.content.ContextCompat

@Composable
actual fun WithPermission(vararg permissions: String, content: @Composable () -> Unit) {
    val isPreviewMode = LocalInspectionMode.current
    val context = LocalContext.current

    if (isPreviewMode) {
        content.invoke()
        return
    }

    // Permission state - check if all permissions are effectively granted
    var allPermissionsGranted by remember(permissions.contentHashCode()) {
        mutableStateOf(permissions.all { isEffectivelyGranted(it, context) })
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        // After permission request, check all permissions again
        allPermissionsGranted = permissions.all { isEffectivelyGranted(it, context) }
    }

    LaunchedEffect(permissions.contentHashCode()) {
        val permissionsToRequest = permissions.filter { permission ->
            !isEffectivelyGranted(permission, context) && needsRuntimeRequest(permission)
        }

        if (permissionsToRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            // All permissions are either granted or don't need runtime request
            allPermissionsGranted = permissions.all { isEffectivelyGranted(it, context) }
        }
    }

    if (allPermissionsGranted) {
        content.invoke()
    }
}

fun isEffectivelyGranted(permission: String, context: Context): Boolean {
    // WRITE_EXTERNAL_STORAGE is automatically granted on Android 10+ (API 29+)
    if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || isGranted(permission, context)
    }

    return isGranted(permission, context)
}

private fun isGranted(permission: String, context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

private fun needsRuntimeRequest(permission: String): Boolean {
    // WRITE_EXTERNAL_STORAGE doesn't need runtime request on Android 10+ (API 29+)
    if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
    }

    return true
}