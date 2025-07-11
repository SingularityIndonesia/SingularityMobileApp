package ui.screen.login

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import utils.CollectSideEffect

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel = koinViewModel()) {
    val state by viewModel.collectAsState()

    CollectSideEffect(viewModel) {

    }

    LoginScreen(
        state = state,
        onIntent = {

        }
    )
}

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onIntent: (LoginScreenIntent) -> Unit
) {
    Scaffold {

    }
}

@Preview
@Composable
private fun Preview() {
    LoginScreen(
        state = LoginScreenState(),
        onIntent = {}
    )
}