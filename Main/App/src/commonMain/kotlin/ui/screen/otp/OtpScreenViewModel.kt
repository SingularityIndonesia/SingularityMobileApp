package ui.screen.otp

import androidx.lifecycle.ViewModel
import model.particle.Email
import model.particle.Otp
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.authentication.AuthenticationService
import service.session.SessionService
import utils.otpIsValid

open class OtpScreenViewModel(
    private val authenticationService: AuthenticationService,
    private val sessionService: SessionService
) : ContainerHost<OtpScreenState, OtpScreenEffect>, ViewModel() {
    override val container: Container<OtpScreenState, OtpScreenEffect> = container(OtpScreenState())

    private var email: Email = Email("")

    fun setEmail(email: Email) {
        this.email = email
    }

    open fun updateOtp(otp: String) = intent {
        reduce {
            state.copy(
                otp = otp,
                otpError = null
            )
        }
    }

    open fun submitOtpVerification() = intent {
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

        val token = authenticationService.authenticateByOtp(Email(email.email), Otp(state.otp))

        val session = token
            .fold(
                onSuccess = {
                    sessionService.start(it)
                },
                onFailure = { Result.failure(it) }
            )

        reduce {
            state.copy(isLoading = false)
        }

        session
            .onSuccess {
                postSideEffect(OtpScreenEffect.NavigateToHome)
            }
            .onFailure { e ->
                postSideEffect(OtpScreenEffect.ShowError(e.message ?: "Login failed"))
            }
    }
}