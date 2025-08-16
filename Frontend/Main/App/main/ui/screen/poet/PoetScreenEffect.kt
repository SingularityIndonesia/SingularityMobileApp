package ui.screen.poet

sealed class PoetScreenEffect {
    data class ShowError(val message: String) : PoetScreenEffect()
}