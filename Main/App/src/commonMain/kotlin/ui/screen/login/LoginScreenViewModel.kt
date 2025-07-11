package ui.screen.login

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.authentication.AuthenticationService

class LoginScreenViewModel(
    private val authenticationService: AuthenticationService
) : ContainerHost<LoginScreenState, LoginScreenEffect>, ViewModel() {
    override val container: Container<LoginScreenState, LoginScreenEffect> = container(LoginScreenState())
}