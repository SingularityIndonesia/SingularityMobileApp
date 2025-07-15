package ui.screen.poet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.SingularityTheme
import ui.designsystem.component.RatioImage
import ui.designsystem.component.TopAppBar
import utils.dateTime
import utils.launchMediaPicker
import utils.requestFocus
import kotlin.time.Duration.Companion.seconds

@Composable
fun PoetScreen() {
    val textFieldState = rememberTextFieldState()
    val mediaUris = remember { mutableStateListOf<String>() }
    val creationDate = remember { dateTime() }

    // auto save
    LaunchedEffect(textFieldState.text, mediaUris) {
        // TODO
    }

    val textFieldFocusRequester = remember { FocusRequester() }

    // autofocus to text field on init
    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus(1.seconds)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = creationDate,
                onMediaSelected = mediaUris::addAll
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            if (mediaUris.isNotEmpty()) {
                Medias(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    uris = mediaUris
                )
            }

            Note(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                state = textFieldState,
                focusRequester = textFieldFocusRequester
            )
        }
    }
}

@Composable
fun Medias(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    uris: List<String>
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
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onSurface.copy(alpha = .1f),
                        RoundedCornerShape(8.dp)
                    ),
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
    val textColor = LocalContentColor.current

    SelectionContainer(
        modifier = modifier
    ) {
        if (state.text.isBlank())
            Text(
                "Tell me story",
                style = bodyText.copy(
                    color = textColor.copy(
                        alpha = .5f
                    )
                )
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
    onMediaSelected: (uris: List<String>) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        titleText = title,
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
        PoetScreen()
    }
}

@Composable
fun imageSizePreference(): IntSize {
    // TODO: assume maximum ratio is 16:10 for landscape picture,
    //  therefore; the preference height is 10/16 * available width space.
    //  also because
    TODO()
}