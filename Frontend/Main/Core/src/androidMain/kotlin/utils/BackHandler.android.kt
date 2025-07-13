package utils

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun OnBackHandler(bloc: () -> Unit) {
    BackHandler {
        bloc.invoke()
    }
}