//package core.ui.designsystem.component
//
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Snackbar
//import androidx.compose.material3.SnackbarData
//import androidx.compose.material3.SnackbarDuration
//import androidx.compose.material3.SnackbarVisuals
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//
//
////class ErrorSnackBar(
////    override val actionLabel: String,
////    override val message: String
////) : SnackbarVisuals {
////    override val duration: SnackbarDuration = SnackbarDuration.Indefinite
////    override val withDismissAction: Boolean = false
////}
////
////@Composable
////fun SErrorSnackBar(
////    message: String,
////    actionLabel: String,
////    modifier: Modifier = Modifier,
////    /*actionOnNewLine: Boolean = false,*/
////    /*shape: Shape = SnackbarDefaults.shape,*/
////    /*containerColor: Color = SnackbarDefaults.color,*/
////    /*contentColor: Color = SnackbarDefaults.contentColor,*/
////    /*actionColor: Color = SnackbarDefaults.actionColor,*/
////    /*actionContentColor: Color = SnackbarDefaults.actionContentColor,*/
////    /*dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,*/
////    onActionClicked: () -> Unit,
////) {
////    val snackBarData = remember(message) {
////        object : SnackbarData {
////            override val visuals: SnackbarVisuals = ErrorSnackBar(
////                actionLabel,
////                message
////            )
////
////            override fun dismiss() {}
////
////            override fun performAction() {
////                onActionClicked.invoke()
////            }
////        }
////    }
////
////    Snackbar(
////        snackBarData,
////        modifier = modifier,
////        containerColor = MaterialTheme.colorScheme.errorContainer,
////        contentColor = MaterialTheme.colorScheme.onErrorContainer,
////        actionColor = MaterialTheme.colorScheme.error
////    )
////}