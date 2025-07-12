package ui.screen.otp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import model.particle.Email
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import ui.designsystem.component.LabelLargeText
import ui.navigation.Route
import utils.CollectSideEffect

@Composable
fun OtpScreen(
    viewModel: OtpScreenViewModel = koinViewModel(),
    purpose: Route.OtpPurpose,
    data: String?,
    goToHome: () -> Unit = {}
) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(purpose, data) {
        when (purpose) {
            Route.OtpPurpose.LoginVerification if data != null -> {
                viewModel.setEmail(Email(data))
            }

            else -> {

            }
        }
    }

    CollectSideEffect(viewModel) { effect ->
        when (effect) {
            OtpScreenEffect.NavigateToHome -> {
                goToHome()
            }

            is OtpScreenEffect.ShowError -> {
                scope.launch {
                    snackBarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    OtpScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onIntent = {
            when (it) {
                is OtpScreenIntent.UpdateOtp -> {
                    viewModel.updateOtp(it.otp)
                }

                OtpScreenIntent.SubmitOtpVerification -> {
                    viewModel.submitOtpVerification()
                }
            }
        }
    )
}

@Composable
fun OtpScreen(
    state: OtpScreenState,
    snackBarHostState: SnackbarHostState,
    onIntent: (OtpScreenIntent) -> Unit
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
                text = "Verify Otp",
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(24.dp))
            val otpBuffer = remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = otpBuffer.value,
                onValueChange = {
                    otpBuffer.value = it
                    onIntent(OtpScreenIntent.UpdateOtp(it))
                },
                label = { Text("Otp") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = state.otpError != null,
                supportingText = state.otpError?.let { { Text(it) } },
                enabled = !state.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onIntent(OtpScreenIntent.SubmitOtpVerification) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                }
                LabelLargeText(if (state.isLoading) "Verifying OTP..." else "Verify")
            }
            Spacer(modifier = Modifier.weight(10f))
        }
    }
}