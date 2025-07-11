package ui.screen.otp

import androidx.lifecycle.ViewModel
import model.particle.Email
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.authentication.AuthenticationService
import utils.otpIsValid

class OtpScreenViewModel(
    private val authenticationService: AuthenticationService
) : ContainerHost<OtpScreenState, OtpScreenEffect>, ViewModel() {
    override val container: Container<OtpScreenState, OtpScreenEffect> = container(OtpScreenState())

    fun updateOtp(otp: String) = intent {
        reduce {
            state.copy(
                otp = otp,
                otpError = null
            )
        }
    }

    fun submitOtpVerification() = intent {
        check(state.otp.isNotBlank()) {
            reduce {
                state.copy(otpError = "Otp is required")
            }

            return@intent
        }

        check(otpIsValid(state.otp)) {
            reduce {
                state.copy(otpError = "Please enter a valid OTP")
            }
            return@intent
        }

        // start loading
        reduce {
            state.copy(isLoading = true, otpError = null)
        }

        authenticationService.requestOtp(Email(state.otp))
            .onSuccess {
                postSideEffect(OtpScreenEffect.NavigateToHome)
            }
            .onFailure { e ->
                postSideEffect(OtpScreenEffect.ShowError(e.message ?: "Login failed"))
            }

        reduce {
            state.copy(isLoading = false)
        }
    }
}