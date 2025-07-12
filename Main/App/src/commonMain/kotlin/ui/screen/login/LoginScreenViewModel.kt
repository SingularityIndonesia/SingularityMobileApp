package ui.screen.login

import androidx.lifecycle.ViewModel
import model.particle.Email
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.authentication.AuthenticationService
import utils.isValidEmail

class LoginScreenViewModel(
    private val authenticationService: AuthenticationService
) : ContainerHost<LoginScreenState, LoginScreenEffect>, ViewModel() {
    override val container: Container<LoginScreenState, LoginScreenEffect> = container(LoginScreenState())

    fun updateEmail(email: String) = intent {
        reduce {
            state.copy(
                email = email,
                emailError = null,
                isSubmitButtonEnabled = isValidEmail(email)
            )
        }
    }

    fun submitLogin() = intent {
        check(state.email.isNotBlank()) {
            reduce {
                state.copy(emailError = "Email is required")
            }

            return@intent
        }

        check(isValidEmail(state.email)) {
            reduce {
                state.copy(emailError = "Please enter a valid email address")
            }
            return@intent
        }

        // start loading
        reduce {
            state.copy(
                isLoading = true, emailError = null,
                isSubmitButtonEnabled = false,
                isTextInputEnabled = false
            )
        }

        authenticationService.requestOtp(Email(state.email))
            .onSuccess {
                postSideEffect(LoginScreenEffect.NavigateToOtp)
            }
            .onFailure { e ->
                postSideEffect(LoginScreenEffect.ShowError(e.message ?: "Login failed"))
            }

        reduce {
            state.copy(
                isLoading = false,
                isSubmitButtonEnabled = isValidEmail(state.email),
                isTextInputEnabled = true,
                emailError = null
            )
        }
    }
}