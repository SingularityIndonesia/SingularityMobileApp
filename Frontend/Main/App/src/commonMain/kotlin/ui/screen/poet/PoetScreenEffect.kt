package ui.screen.poet

import ui.screen.login.LoginScreenEffect

sealed class PoetScreenEffect {
    data class ShowError(val message: String) : PoetScreenEffect()
}