package ui.screen.login

data class LoginScreenState(
    val email: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val isTextInputEnabled: Boolean = false,
    val isSubmitButtonEnabled: Boolean = false,
)