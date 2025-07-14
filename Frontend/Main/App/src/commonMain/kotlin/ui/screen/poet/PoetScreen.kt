package ui.screen.poet

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.SingularityTheme
import ui.designsystem.component.TopAppBar
import utils.date
import utils.launchMediaPicker
import utils.requestFocus
import kotlin.time.Duration.Companion.seconds

@Composable
fun PoetScreen() {
    val textFieldState = rememberTextFieldState()
    val textFieldFocusRequester = remember { FocusRequester() }
    val mediaUris = remember { mutableStateListOf<String>() }

    // autofocus to text field on init
    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus(1.seconds)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onMediaSelected = { uris ->
                    mediaUris.addAll(uris)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            if (mediaUris.isNotEmpty()) {
                Medias(mediaUris)
            }

            Note(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = textFieldState,
                focusRequester = textFieldFocusRequester
            )
        }
    }
}

@Composable
fun Medias(uris: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(uris) { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Selected media",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
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
    val textMeasurer = rememberTextMeasurer()
    val bodyText = MaterialTheme.typography.bodyLarge
    val textColor = LocalContentColor.current

    SelectionContainer(
        modifier = modifier
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    if (state.text.isBlank())
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "Tell me story",
                            style = bodyText.copy(
                                color = textColor.copy(
                                    alpha = .5f
                                )
                            ),
                        )
                    drawContent()
                }
                .focusRequester(focusRequester),
            textStyle = bodyText,
            state = state,
        )
    }
}

@Composable
fun TopAppBar(
    onMediaSelected: (uris: List<String>) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        titleText = date(),
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
