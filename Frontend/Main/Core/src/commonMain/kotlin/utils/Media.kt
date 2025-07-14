package utils

/**
 * Launches a media picker to select images and videos
 * @param onMediaSelected Callback invoked when media is selected with the list of URIs
 * @param onCancelled Callback invoked when the picker is cancelled
 */
expect fun launchMediaPicker(
    onMediaSelected: (List<String>) -> Unit,
    onCancelled: () -> Unit = {}
)

/**
 * Checks if media picker is available on the current platform
 * @return true if media picker is available, false otherwise
 */
expect fun isMediaPickerAvailable(): Boolean
