package designsystem.component.experimental

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import designsystem.component.RatioImage
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.min

@Immutable
class VerticalDoubleRowFlowScope {
    private val _items = mutableStateListOf<Pair<Any, @Composable () -> Unit>>()
    val items = _items as List<Pair<Any, @Composable () -> Unit>>

    fun item(key: Any, bloc: @Composable () -> Unit) {
        _items.removeAll { it.first == key }
        _items.add(key to bloc)
    }
}

@Composable
fun VerticalDoubleRowFlow(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    builder: VerticalDoubleRowFlowScope.() -> Unit = {}
) {
    val density = LocalDensity.current
    val scope = VerticalDoubleRowFlowScope().apply { builder() }
    val contentTopPadding = rememberUpdatedState(contentPadding.calculateTopPadding())
    val contentBottomPadding = rememberUpdatedState(contentPadding.calculateBottomPadding())
    val contentPaddingStart = rememberUpdatedState(contentPadding.calculateStartPadding(LayoutDirection.Rtl))
    val contentPaddingEnd = rememberUpdatedState(contentPadding.calculateEndPadding(LayoutDirection.Ltr))
    val panelSize = remember { mutableStateOf(IntSize.Zero) }
    val totalHorizontalPadding = rememberUpdatedState(contentPaddingStart.value + contentPaddingEnd.value)
    val availableWidth =
        rememberUpdatedState((panelSize.value.width / density.density).dp - totalHorizontalPadding.value - horizontalGap)
    val itemWidth = rememberUpdatedState(availableWidth.value / 2f)
    val itemWidthPx = rememberUpdatedState(itemWidth.value.value * density.density)
    val ratios = remember { mutableMapOf<Any, Float>() }
    val itemRect = remember { mutableMapOf<Any, Rect>() }

    Box(
        modifier = modifier
            .onSizeChanged { panelSize.value = it }
            .verticalScroll(scrollState)
    ) {
        val spacerHeight = rememberUpdatedState(((itemRect.values.maxOfOrNull { it.bottom } ?: 0f) / density.density).dp)

        LaunchedEffect(spacerHeight.value) {
            println("asdnlasdn ${spacerHeight.value}")
        }
        Spacer(
            modifier = Modifier
                .padding(bottom = contentBottomPadding.value)
                .size(width = 0.dp, height = spacerHeight.value)
        )

        scope.items.indices.map { i ->
            val item = remember(i) { scope.items[i] }
            val position = defineYPositionRelative(
                key = item.first,
                keys = scope.items.map { it.first },
                ratios = ratios,
                maxWidth = itemWidthPx.value,
                verticalGap = verticalGap,
            )
            val isLeft = rememberUpdatedState(position.value.first)
            val offsetTop = rememberUpdatedState(position.value.second)

            Box(
                modifier = Modifier
                    .width(itemWidth.value)
                    .offset(y = offsetTop.value)
                    .offset(y = contentTopPadding.value)
                    .offset(x = if (isLeft.value) contentPaddingStart.value else -contentPaddingEnd.value)
                    .align(if (isLeft.value) Alignment.TopStart else Alignment.TopEnd)
                    .onGloballyPositioned {
                        itemRect[item.first] = it.boundsInParent()
                    }
                    .onSizeChanged {
                        ratios[item.first] = it.width.toFloat() / it.height.toFloat()
                    }
            ) {
                item.second.invoke()
            }
        }
    }
}


// return position left if result.first == true
// result.second is the ratio relative magnitude
@Composable
fun <T> defineYPositionRelative(
    key: T,
    keys: List<T>,
    ratios: Map<T, Float>,
    maxWidth: Float,
    verticalGap: Dp,
): State<Pair<Boolean, Dp>> {
    val density = LocalDensity.current
    var leftOccupation = 0f
    var rightOccupation = 0f
    val verticalGapPx = verticalGap.value * density.density

    for (e: T in keys) {
        if (e == key) break

        val keyRatio = ratios[e] ?: 1f
        if (leftOccupation <= rightOccupation) {
            leftOccupation += (maxWidth / keyRatio) + verticalGapPx
        } else {
            rightOccupation += (maxWidth / keyRatio) + verticalGapPx
        }
    }

    val isLeft = leftOccupation <= rightOccupation
    val top = min(leftOccupation, rightOccupation) / density.density

    return rememberUpdatedState(isLeft to top.dp)
}

@Preview
@Composable
fun VerticalDoubleRowFlowPreview() {
    val images = remember {
        mutableStateListOf(
            "https://cnc-magazine.oramiland.com/parenting/images/kim-da-hyun.width-800.format-webp.webp",
            "https://pbs.twimg.com/media/GllvhtPW4AAUnRR?format=jpg&name=large",
            "https://cdn.shopify.com/s/files/1/0469/3927/5428/files/TWICE-Dahyun-for-A-pieu-2023-Juicy-Pang-Tint-documents-2.jpg?v=1738754206",
            "https://i.pinimg.com/736x/3f/10/a6/3f10a655fbe33402d858bffa7193df16.jpg",
            "https://burst.shopifycdn.com/photos/fashion-model-in-fur.jpg?width=1000&format=pjpg&exif=0&iptc=0",
            "https://images.saatchiart.com/saatchi/2199861/art/10341725/9404341-JUYLYEKQ-32.jpg"
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
        images.addAll(
            listOf(
                "https://media.istockphoto.com/id/814423752/photo/eye-of-model-with-colorful-art-make-up-close-up.jpg?s=612x612&w=0&k=20&c=l15OdMWjgCKycMMShP8UK94ELVlEGvt7GmB_esHWPYE=",
                "https://img.freepik.com/free-photo/closeup-scarlet-macaw-from-side-view-scarlet-macaw-closeup-head_488145-3540.jpg?semt=ais_hybrid&w=740",
                "https://images.panda.org/assets/images/pages/welcome/orangutan_1600x1000_279157.jpg",
                "https://media.istockphoto.com/id/2148595463/photo/african-elephant-in-wilderness-at-sunset.jpg?s=612x612&w=0&k=20&c=WmZfQYFpQOKTIyB95U6NQ6h8WQM-Pjp9TCMvF63-Xf4=",
                "https://thumbs.dreamstime.com/b/i-m-hungry-ma-12739742.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmfuHcvQcxVfU6YzM_ty5dfnSmojk8h2NSoi58XxFOzbh1ceRx9En9nnZUiVNqhH6IX-M&usqp=CAU"
            )
        )
    }

    VerticalDoubleRowFlow(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        verticalGap = 4.dp,
        horizontalGap = 4.dp,
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        images.map {
            item(it) {
                RatioImage(
                    modifier = Modifier
                        .background(Color.White)
                        .border(BorderStroke(1.px, Color.Black.copy(alpha = .5f)))
                        .padding(16.dp)
                        .fillMaxWidth(),
                    model = it
                )
            }
        }
    }
}

val Number.px: Dp
    @Composable get() {
        val density = LocalDensity.current
        return this.toFloat().dp / density.density
    }