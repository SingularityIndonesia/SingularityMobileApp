package ui.screen.poet

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import service.vault.VaultService
import ui.screen.poet.PoetScreenEffect.ShowError

class PoetScreenViewModel(
    private val vaultService: VaultService
) : ViewModel(), ContainerHost<PoetScreenState, PoetScreenEffect> {

    override val container: Container<PoetScreenState, PoetScreenEffect> = container(PoetScreenState())

    init {
        getNewDocument()
    }

    fun getNewDocument() = intent {
        reduce {
            state.copy(
                isLoading = true
            )
        }

        vaultService.newDocument()
            .onSuccess {
                reduce {
                    state.copy(
                        document = it,
                        isLoading = false
                    )
                }
            }
            .onFailure {
                postSideEffect(ShowError(it.message ?: "Unknown Erro"))
                reduce {
                    state.copy(
                        isLoading = false
                    )
                }
            }
    }

    fun setTitle(title: String) = intent {
        reduce {
            // fixme: use lenses
            state.copy(
                document = state.document.copy(
                    content = state.document.content.copy(
                        title = title
                    )
                )
            )
        }
    }

    fun addMedia(uris: List<String>) = intent {
        val newUris = state.document.content.mediaUris + uris

        // fixme: use lenses
        reduce {
            state.copy(
                document = state.document.copy(
                    content = state.document.content.copy(
                        mediaUris = newUris
                    )
                )
            )
        }
    }

    fun removeMedia(uri: String) = intent {
        val newUris = state.document.content.mediaUris.filterNot { it == uri }

        // fixme: use lenses
        reduce {
            state.copy(
                document = state.document.copy(
                    content = state.document.content.copy(
                        mediaUris = newUris
                    )
                )
            )
        }
    }

    fun saveDocument() = intent {
        // reduce {
        //     state.copy(isLoading = true)
        // }

        // vaultService.newDocument()
        //     .onSuccess { document ->
        //         reduce {
        //             state.copy(
        //                 isLoading = false,
        //                 documentId = document.id
        //             )
        //         }
        //     }
        //     .onFailure { exception ->
        //         reduce {
        //             state.copy(
        //                 isLoading = false,
        //             )
        //         }

        //         postSideEffect(
        //             PoetScreenEffect.ShowError(exception.message ?: "Unknown error occurred")
        //         )
        //     }
    }
}
