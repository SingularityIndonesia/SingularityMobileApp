package ui.screen.otp.viemodel

import kotlinx.coroutines.Job
import model.particle.Email
import model.particle.Otp
import org.orbitmvi.orbit.ContainerHost
import service.authentication.AuthenticationService
import service.session.SessionService
import ui.screen.otp.OtpScreenEffect
import ui.screen.otp.OtpScreenState
import utils.isValidOtp

interface LoginVerificationOtpViewModel {
    fun initLoginVerificationOtpViewModel(container: ContainerHost<OtpScreenState, OtpScreenEffect>)
    val email: Email
    fun setEmailForLoginVerification(email: Email)
    fun submitOtpLoginVerification(): Job
}

class LoginVerificationOtpViewModelImpl(
    private val authenticationService: AuthenticationService,
    private val sessionService: SessionService
) : LoginVerificationOtpViewModel {
    private lateinit var container: ContainerHost<OtpScreenState, OtpScreenEffect>

    override fun initLoginVerificationOtpViewModel(container: ContainerHost<OtpScreenState, OtpScreenEffect>) {
        this.container = container
    }

    override var email: Email = Email("")
        private set

    override fun setEmailForLoginVerification(email: Email) {
        this.email = email
    }

    override fun submitOtpLoginVerification() = with(container) {
        intent {
            check(state.otp.isNotBlank()) {
                reduce {
                    state.copy(otpError = "Otp is required")
                }

                return@intent
            }

            check(isValidOtp(state.otp)) {
                reduce {
                    state.copy(otpError = "Please enter a valid OTP")
                }
                return@intent
            }

            // start loading
            reduce {
                state.copy(isLoading = true, otpError = null, isSubmitButtonEnabled = false, isTextInputEnabled = false)
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

            state.copy(
                isLoading = true,
                otpError = null,
                isSubmitButtonEnabled = isValidOtp(state.otp),
                isTextInputEnabled = true
            )
        }
    }
}