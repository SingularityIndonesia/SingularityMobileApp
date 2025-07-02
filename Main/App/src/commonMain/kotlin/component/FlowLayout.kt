package component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

class FlowLayoutScope {
    internal val leftMagnitude = mutableFloatStateOf(0f)
    internal val rightMagnitude = mutableFloatStateOf(0f)

    private val _items = mutableStateMapOf<Any, @Composable () -> Unit>()
    val items = _items as Map<Any, @Composable () -> Unit>

    private val _aspectRatios = mutableStateMapOf<Any, Float>()
    val aspectRatios = _aspectRatios as Map<Any, Float>

    fun item(key: Any, ratio: Float, bloc: @Composable () -> Unit) {
        _aspectRatios[key] = ratio
        _items[key] = bloc
    }
}

@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    builder: FlowLayoutScope.() -> Unit = {}
) {
    val scope = FlowLayoutScope().apply(builder)
    val density = LocalDensity.current
    val screenWidth = remember { mutableStateOf(0.dp) }
    val sectionWidth by rememberUpdatedState(
        (screenWidth.value
                - contentPadding.calculateStartPadding(LayoutDirection.Ltr)
                - contentPadding.calculateEndPadding(LayoutDirection.Rtl)
                - horizontalGap)
                / 2
    )

    Column(
        modifier = modifier
            .onSizeChanged {
                screenWidth.value = (it.width / density.density).dp
            }
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = contentPadding.calculateTopPadding(), bottom = contentPadding.calculateBottomPadding())
                .wrapContentHeight()
        ) {
            check(sectionWidth > 0.dp) { return@Box }

            scope.items.entries.indices.map { index ->
                val item = remember { scope.items.toList()[index] }

                key(item.first) {
                    val ratio = remember { scope.aspectRatios.toList()[index].second }
                    val isPreferLeft = remember { scope.leftMagnitude.value <= scope.rightMagnitude.value }
                    val topOffset =
                        remember { if (isPreferLeft) scope.leftMagnitude.value else scope.rightMagnitude.value }

                    if (isPreferLeft)
                        scope.leftMagnitude.value += (sectionWidth.value * density.density) / ratio
                    else
                        scope.rightMagnitude.value += (sectionWidth.value * density.density) / ratio

                    val shouldApplyGap = remember { topOffset > 0f }
                    if (shouldApplyGap) {
                        if (isPreferLeft)
                            scope.leftMagnitude.value += verticalGap.value * density.density
                        else
                            scope.rightMagnitude.value += verticalGap.value * density.density
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = (topOffset / density.density).dp)
                            .padding(top = if (shouldApplyGap) verticalGap else 0.dp)
                            .padding(
                                start = if (isPreferLeft) contentPadding.calculateLeftPadding(LayoutDirection.Rtl) else 0.dp,
                                end = if (!isPreferLeft) contentPadding.calculateLeftPadding(LayoutDirection.Ltr) else 0.dp,
                            )
                            .width(sectionWidth)
                            .aspectRatio(ratio)
                            .align(if (isPreferLeft) Alignment.TopStart else Alignment.TopEnd)
                    ) {
                        item.second.invoke()
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    FlowLayout(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        horizontalGap = 16.dp,
        verticalGap = 16.dp,
        contentPadding = PaddingValues(16.dp)
    ) {
        item(1, 1f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item1"
            )
        }
        item(2, 1 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item2"
            )
        }
        item(3, 3 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item3"
            )
        }
        item(4, 10 / 16f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item4"
            )
        }
        item(5, 3 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item5"
            )
        }
        item(6, 3 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item6"
            )
        }

        item(7, 3 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item7"
            )
        }
        item(8, 10 / 16f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item8"
            )
        }
        item(9, 3 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item9"
            )
        }
        item(10, 3 / 2f) {
            Text(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "item10"
            )
        }
    }
}