package ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import ui.designsystem.component.LabelLargeText
import utils.CollectSideEffect

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel = koinViewModel()) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    CollectSideEffect(viewModel) { effect ->
        when (effect) {
            is LoginScreenEffect.NavigateToOtp -> {
                scope.launch {
                    snackBarHostState.showSnackbar("Navigate to OTP screen")
                }
            }

            is LoginScreenEffect.ShowError -> {
                scope.launch {
                    snackBarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    LoginScreen(
        state = state,
        onIntent = {
            when (it) {
                is LoginScreenIntent.UpdateEmail -> viewModel.updateEmail(it.email)
                LoginScreenIntent.SubmitLogin -> viewModel.submitLogin()
            }
        },
        snackBarHostState = snackBarHostState
    )
}

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onIntent: (LoginScreenIntent) -> Unit,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(8f))
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(24.dp))
            val emailBuffer = remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = emailBuffer.value,
                onValueChange = {
                    emailBuffer.value = it
                    onIntent(LoginScreenIntent.UpdateEmail(it))
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = state.emailError != null,
                supportingText = state.emailError?.let { { Text(it) } },
                enabled = !state.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onIntent(LoginScreenIntent.SubmitLogin) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                }
                LabelLargeText(if (state.isLoading) "Requesting OTP..." else "Login")
            }
            Spacer(modifier = Modifier.weight(10f))
        }
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

@Preview
@Composable
private fun PreviewWithEmail() {
    LoginScreen(
        state = LoginScreenState(email = "user@example.com"),
        onIntent = {}
    )
}

@Preview
@Composable
private fun PreviewLoading() {
    LoginScreen(
        state = LoginScreenState(
            email = "user@example.com",
            isLoading = true
        ),
        onIntent = {}
    )
}

@Preview
@Composable
private fun PreviewWithError() {
    LoginScreen(
        state = LoginScreenState(
            email = "invalid-email",
            emailError = "Please enter a valid email address"
        ),
        onIntent = {}
    )
}