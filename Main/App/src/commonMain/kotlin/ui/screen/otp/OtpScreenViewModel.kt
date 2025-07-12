package ui.screen.otp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.particle.Email
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.authentication.AuthenticationService
import service.session.SessionService
import ui.navigation.Route
import ui.screen.otp.viemodel.LoginVerificationOtpViewModel
import ui.screen.otp.viemodel.LoginVerificationOtpViewModelImpl
import utils.isValidOtp

class OtpScreenViewModel(
    private val authenticationService: AuthenticationService,
    private val sessionService: SessionService
) : ContainerHost<OtpScreenState, OtpScreenEffect>,
    LoginVerificationOtpViewModel by LoginVerificationOtpViewModelImpl(authenticationService, sessionService),
    ViewModel() {

    override val container: Container<OtpScreenState, OtpScreenEffect> = container(OtpScreenState())

    init {
        initLoginVerificationOtpViewModel(this)
    }

    var otpPurpose by mutableStateOf(Route.OtpPurpose.LOGIN_VERIFICATION)

    fun setOtpPurpose(purpose: Route.OtpPurpose, data: String?) {
        otpPurpose = purpose

        when (purpose) {
            Route.OtpPurpose.LOGIN_VERIFICATION if data != null -> {
                setEmailForLoginVerification(Email(data))
            }

            else -> {

            }
        }
    }

    fun updateOtp(otp: String) = intent {
        reduce {
            state.copy(
                otp = otp,
                otpError = null,
                isSubmitButtonEnabled = isValidOtp(otp)
            )
        }
    }
}