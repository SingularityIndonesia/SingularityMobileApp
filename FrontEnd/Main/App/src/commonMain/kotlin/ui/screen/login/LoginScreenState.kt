package ui.screen.login

data class LoginScreenState(
    val email: String = "singularity.universe@gmail.com",
    val enableEmailInputBuffering: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val isTextInputEnabled: Boolean = true,
    val isSubmitButtonEnabled: Boolean = true,
)