package designsystem.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.px

@Composable
fun <T> Waterfall(
    modifier: Modifier = Modifier,
    items: List<T> = emptyList(),
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    rowCount: Int = 2,
    content: @Composable (T) -> Unit = {}
) {
    val density = LocalDensity.current
    val panelSize = remember { mutableStateOf(IntSize.Zero) }
    val ratios = remember { mutableStateMapOf<T, Float>() }
    val itemRect = remember { mutableStateMapOf<T, Rect>() }

    val contentTopPadding = rememberUpdatedState(contentPadding.calculateTopPadding())
    val contentBottomPadding = rememberUpdatedState(contentPadding.calculateBottomPadding())
    val contentPaddingStart = rememberUpdatedState(contentPadding.calculateStartPadding(LayoutDirection.Rtl))
    val contentPaddingEnd = rememberUpdatedState(contentPadding.calculateEndPadding(LayoutDirection.Ltr))
    val totalHorizontalPadding = rememberUpdatedState(contentPaddingStart.value + contentPaddingEnd.value)
    val availableWidth =
        rememberUpdatedState((panelSize.value.width / density.density).dp - totalHorizontalPadding.value - horizontalGap * (rowCount - 1))
    val itemWidth = rememberUpdatedState(availableWidth.value / rowCount)
    val itemWidthPx = rememberUpdatedState(itemWidth.value.value * density.density)
    val spacerHeight = rememberUpdatedState(((itemRect.values.maxOfOrNull { it.bottom } ?: 0f) / density.density).dp)

    Box(
        modifier = modifier
            .onSizeChanged { panelSize.value = it }
            .verticalScroll(scrollState)
    ) {
        // Trigger Scrollable by drawing spacer with height == total height
        Spacer(
            modifier = Modifier
                .padding(bottom = contentBottomPadding.value)
                .size(width = 0.dp, height = spacerHeight.value)
        )

        items.map { item ->
            val position = defineYPositionRelative(
                key = item,
                keys = items,
                ratios = ratios,
                maxWidth = itemWidthPx.value,
                verticalGap = verticalGap,
                rowCount = rowCount
            )
            val rowIndex = rememberUpdatedState(position.value.first)
            val offsetTop = rememberUpdatedState(position.value.second)
            val offsetStart = rememberUpdatedState(itemWidth.value * rowIndex.value)
            val horizontalGapOffsetExtra = rememberUpdatedState(horizontalGap * rowIndex.value)

            Box(
                modifier = Modifier
                    .width(itemWidth.value)
                    .wrapContentHeight()
                    .offset(y = offsetTop.value)
                    .offset(y = contentTopPadding.value)
                    .offset(x = offsetStart.value)
                    .offset(x = contentPaddingStart.value)
                    .offset(x = horizontalGapOffsetExtra.value)
                    .onSizeChanged {
                        ratios[item] = it.width.toFloat() / it.height.toFloat()
                    }
                    .onGloballyPositioned {
                        itemRect[item] = it.boundsInParent()
                    }
            ) {
                content.invoke(item)
            }
        }
    }
}

// result.first is the row index
// result.second is the ratio relative magnitude
@Composable
private fun <T> defineYPositionRelative(
    key: T,
    keys: List<T>,
    ratios: Map<T, Float>,
    maxWidth: Float,
    verticalGap: Dp,
    rowCount: Int
): State<Pair<Int, Dp>> {
    val density = LocalDensity.current
    val rowOccupation = mutableListOf(*(0..rowCount - 1).map { 0f }.toTypedArray())
    val verticalGapPx = verticalGap.value * density.density

    for (e: T in keys) {
        if (e == key) break

        val keyRatio = ratios[e] ?: 1f
        val minimumOccupiedRowIndex = rowOccupation
            .foldIndexed(0 to Float.MAX_VALUE) { i, acc, n ->
                if (acc.second > n)
                    i to n
                else acc
            }
            .first
        rowOccupation[minimumOccupiedRowIndex] += (maxWidth / keyRatio) + verticalGapPx
    }

    val row = rowOccupation
        .foldIndexed(0 to Float.MAX_VALUE) { i, acc, n ->
            if (acc.second > n)
                i to n
            else acc
        }
    val index = row.first
    val top = (row.second / density.density).dp

    return rememberUpdatedState(index to top)
}

@Preview
@Composable
fun WaterfallPreview() {
    val images = remember {
        listOf(
            "https://media.istockphoto.com/id/814423752/photo/eye-of-model-with-colorful-art-make-up-close-up.jpg?s=612x612&w=0&k=20&c=l15OdMWjgCKycMMShP8UK94ELVlEGvt7GmB_esHWPYE=",
            "https://cnc-magazine.oramiland.com/parenting/images/kim-da-hyun.width-800.format-webp.webp",
            "https://burst.shopifycdn.com/photos/fashion-model-in-fur.jpg?width=1000&format=pjpg&exif=0&iptc=0",
            "https://cdn.shopify.com/s/files/1/0469/3927/5428/files/TWICE-Dahyun-for-A-pieu-2023-Juicy-Pang-Tint-documents-2.jpg?v=1738754206",
            "https://i.pinimg.com/736x/3f/10/a6/3f10a655fbe33402d858bffa7193df16.jpg",
            "https://images.saatchiart.com/saatchi/2199861/art/10341725/9404341-JUYLYEKQ-32.jpg",
            "https://pbs.twimg.com/media/GllvhtPW4AAUnRR?format=jpg&name=large",
            "https://img.freepik.com/free-photo/closeup-scarlet-macaw-from-side-view-scarlet-macaw-closeup-head_488145-3540.jpg?semt=ais_hybrid&w=740",
            "https://images.panda.org/assets/images/pages/welcome/orangutan_1600x1000_279157.jpg",
            "https://media.istockphoto.com/id/2148595463/photo/african-elephant-in-wilderness-at-sunset.jpg?s=612x612&w=0&k=20&c=WmZfQYFpQOKTIyB95U6NQ6h8WQM-Pjp9TCMvF63-Xf4=",
            "https://thumbs.dreamstime.com/b/i-m-hungry-ma-12739742.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmfuHcvQcxVfU6YzM_ty5dfnSmojk8h2NSoi58XxFOzbh1ceRx9En9nnZUiVNqhH6IX-M&usqp=CAU"
        )
    }

    val items = remember { (0..30).toList() }

    Waterfall(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        items = items,
        verticalGap = 4.dp,
        horizontalGap = 4.dp,
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        rowCount = 5
    ) { item ->
        RatioImage(
            modifier = Modifier
                .background(Color.White)
                .border(BorderStroke(1.px, Color.Black.copy(alpha = .5f)))
                .padding(8.dp)
                .fillMaxWidth(),
            model = images.random()
        )
    }
}
