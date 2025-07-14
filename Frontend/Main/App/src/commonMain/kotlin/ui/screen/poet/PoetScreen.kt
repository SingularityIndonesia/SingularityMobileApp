package ui.screen.poet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.DesignToken
import ui.designsystem.SingularityTheme
import ui.designsystem.component.TopAppBar
import utils.date
import utils.requestFocus
import kotlin.time.Duration.Companion.seconds

@Composable
fun PoetScreen() {
    val textFieldState = rememberTextFieldState()
    val textFieldFocusRequester = remember { FocusRequester() }
    val textMeasurer = rememberTextMeasurer()

    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus(1.seconds)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                titleText = date(),
                onAddMedia = {

                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            val bodyText = MaterialTheme.typography.bodyLarge
            val textColor = LocalContentColor.current
            SelectionContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            if (textFieldState.text.isBlank())
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
                        .focusRequester(textFieldFocusRequester),
                    textStyle = bodyText,
                    state = textFieldState,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SingularityTheme {
        PoetScreen()
    }
}
