package ui.screen.poet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.FlowPreview
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.vault.VaultService

class PoetScreenViewModel(
    private val vaultService: VaultService
) : ViewModel(), ContainerHost<PoetScreenState, PoetScreenEffect> {

    override val container: Container<PoetScreenState, PoetScreenEffect> = container(PoetScreenState())

    @OptIn(FlowPreview::class)
    private fun setupAutoSave() {
        // Auto-save when text changes
//        container.stateFlow.value.textFieldState
//            .textAsFlow()
//            .debounce(2.seconds)
//            .distinctUntilChanged()
//            .onEach { text ->
//                if (text.isNotBlank()) {
//                    handleIntent(PoetScreenIntent.SaveDocument(container.stateFlow.value.title))
//                }
//            }
//            .launchIn(viewModelScope)
    }

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(
                title = title
            )
        }
    }

    fun addMedia(uris: List<String>) = intent {
        reduce {
            state.copy(
                mediaUris = state.mediaUris + uris
            )
        }
    }

    fun removeMedia(uri: String) = intent {
        reduce {
            state.copy(
                mediaUris = state.mediaUris.filter { it != uri }
            )
        }
    }

    fun saveDocument() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        vaultService.newDocument()
            .onSuccess { document ->
                reduce {
                    state.copy(
                        isLoading = false,
                        documentId = document.id
                    )
                }
            }
            .onFailure { exception ->
                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }

                postSideEffect(
                    PoetScreenEffect.ShowError(exception.message ?: "Unknown error occurred")
                )
            }
    }
}
