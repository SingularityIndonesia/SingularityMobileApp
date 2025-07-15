package ui.screen.poet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import designsystem.SingularityTheme
import designsystem.component.RatioImage
import designsystem.component.TopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import utils.CollectSideEffect
import utils.launchMediaPicker
import utils.requestFocus
import utils.toDateTime
import kotlin.time.Duration.Companion.seconds

@Composable
fun PoetScreen(
    viewModel: PoetScreenViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    CollectSideEffect(viewModel) {
        when (it) {
            is PoetScreenEffect.ShowError -> snackBarHostState.showSnackbar(it.message)
        }
    }

    PoetScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onIntent = {
            when (it) {
                is PoetScreenIntent.UpdateTitle -> viewModel.setTitle(it.title)
                is PoetScreenIntent.AddMedia -> viewModel.addMedia(it.uris)
                is PoetScreenIntent.RemoveMedia -> viewModel.removeMedia(it.uri)
                is PoetScreenIntent.SaveDocument -> viewModel.saveDocument()
            }
        }
    )
}

@Composable
fun PoetScreen(
    state: PoetScreenState,
    snackBarHostState: SnackbarHostState,
    onIntent: (PoetScreenIntent) -> Unit
) {
    val textFieldFocusRequester = remember { FocusRequester() }

    // autofocus to text field on init
    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus(1.seconds)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = state.document.content.title,
                subTitle = state.document.dateCreated.toDateTime(),
                onMediaSelected = {
                    onIntent.invoke(PoetScreenIntent.AddMedia(it))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                if (state.document.content.media.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Medias(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        uris = state.document.content.media.map { it.uri },
                        onRemoveMedia = { uri ->
                            onIntent(PoetScreenIntent.RemoveMedia(uri))
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Note(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    state = state.textFieldState,
                    focusRequester = textFieldFocusRequester
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Loading indicator
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun Medias(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    uris: List<String>,
    onRemoveMedia: (String) -> Unit = {}
) {
    val density = LocalDensity.current
    val maxRatio = remember { 16f / 10f }

    var availableWidth by remember { mutableStateOf(0) }
    val maxHeight = remember(availableWidth, density) {
        (availableWidth / density.density / maxRatio).dp
    }

    LazyRow(
        modifier = modifier
            .onSizeChanged {
                availableWidth = it.width
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding
    ) {
        items(uris) { uri ->
            RatioImage(
                modifier = Modifier
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onSurface.copy(alpha = .1f),
                    )
                    .padding(16.dp),
                model = uri,
                maxHeight = maxHeight
            )
        }
    }
}

@Composable
fun Note(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    val bodyText = MaterialTheme.typography.bodyLarge

    SelectionContainer(
        modifier = modifier
    ) {
        if (state.text.isBlank())
            Text(
                "Tell me story",
                style = bodyText,
                color = LocalContentColor.current.copy(alpha = .6f)
            )

        BasicTextField(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester),
            textStyle = bodyText,
            state = state,
        )
    }
}

@Composable
fun TopAppBar(
    title: String,
    subTitle: String? = null,
    onMediaSelected: (uris: List<String>) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        titleText = title,
        subTitle = subTitle,
        onAddMedia = {
            launchMediaPicker(
                onMediaSelected = onMediaSelected,
                onCancelled = onCancel
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    SingularityTheme {
        PoetScreen(
            state = PoetScreenState(),
            snackBarHostState = remember { SnackbarHostState() },
            onIntent = {},
        )
    }
}