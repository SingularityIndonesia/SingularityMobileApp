package ui.screen.poet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import designsystemold.SingularityTheme
import designsystemold.component.RatioImage
import designsystemold.component.TopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import utils.CollectSideEffect
import utils.MediaPicker
import utils.requestFocus
import kotlin.time.Duration.Companion.seconds

@Composable
fun PoetScreen(
    viewModel: PoetScreenViewModel = koinViewModel(),
    onNavigateBack: (() -> Unit) = {},
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
                is PoetScreenIntent.NavigateBack -> onNavigateBack.invoke()
            }
        }
    )
}

@Composable
fun PoetScreen(
    state: PoetScreenState,
    snackBarHostState: SnackbarHostState,
    onIntent: (PoetScreenIntent) -> Unit,
) {
    val textFieldFocusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()

    // autofocus to text field on init
    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus(1.seconds)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            TopAppBar(
                title = state.title,
                subTitle = state.dateCreated,
                onNavigateBack = {},
                onMediaSelected = {
                    onIntent.invoke(PoetScreenIntent.AddMedia(it))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            if (state.mediaUris.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Medias(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    uris = state.mediaUris.map { it },
                    onRemoveMedia = { uri ->
                        onIntent(PoetScreenIntent.RemoveMedia(uri))
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Note(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                state = state.textFieldState,
                focusRequester = textFieldFocusRequester,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                // Loading indicator

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
                    .padding(16.dp)
                    .height(maxHeight),
                model = uri,
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

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        textStyle = bodyText,
        state = state,
        decorator = {
            if (state.text.isEmpty())
                Text(
                    text = "Tell me story",
                    style = bodyText,
                    color = LocalContentColor.current.copy(alpha = .6f)
                )

            SelectionContainer {
                it()
            }
        }
    )
}

@Composable
fun TopAppBar(
    title: String,
    subTitle: String? = null,
    onNavigateBack: (() -> Unit) = {},
    onMediaSelected: (uris: List<String>) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        onNavigateBack = onNavigateBack,
        titleText = title,
        subTitle = subTitle,
        onAddMedia = {
            MediaPicker.launchMediaPicker(
                onMediaSelected = onMediaSelected,
                onCancelled = onCancel
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    val dummyContent = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
        
        Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?
    """.trimIndent()

    val textFieldState = remember { TextFieldState(dummyContent) }

    SingularityTheme {
        PoetScreen(
            state = PoetScreenState(
                title = "Untitled",
                dateCreated = "June 12, 2024",
                textFieldState = textFieldState,
                mediaUris = listOf("1", "2")
            ),
            snackBarHostState = remember { SnackbarHostState() },
            onIntent = {},
        )
    }
}