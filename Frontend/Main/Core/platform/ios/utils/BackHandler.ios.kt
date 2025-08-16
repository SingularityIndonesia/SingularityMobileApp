package utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun OnBackHandler(bloc: () -> Unit) {
    BackHandler {
        bloc.invoke()
    }
}