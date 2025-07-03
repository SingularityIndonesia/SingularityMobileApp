package pane

import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import component.FlowLayout
import kotlinx.coroutines.*
import main.app.generated.resources.Res
import main.app.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

abstract class PagingSource<T> {
    protected val _items = mutableStateListOf<GalleryItemDisplay>()
    val items = _items as List<GalleryItemDisplay>

    protected val _isCanLoadMore = mutableStateOf(true)
    val isCanLoadMore = _isCanLoadMore as State<Boolean>

    protected val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading as State<Boolean>

    protected val _pageIndex = mutableIntStateOf(-1)
    val pageIndex = _pageIndex as IntState

    protected val _error = mutableStateOf<Exception?>(null)
    val error = _error as State<Exception?>

    abstract suspend fun loadMore()
}

data class GalleryItemDisplay(
    val id: String = "",
    val imageUrls: List<String> = emptyList(),
    val postDate: String = ""
)

class GalleryItemDisplayPagingSource(
    private val scope: CoroutineScope
) : PagingSource<GalleryItemDisplay>() {

    override suspend fun loadMore() {
        check(!_isLoading.value) { return }
        _isLoading.value = true

        withContext(Dispatchers.IO) {
            delay(1000L)
            _pageIndex.intValue += 1
            val nextPageIndex = pageIndex.value

            _items += (0..9).map { it + nextPageIndex * 10 }.map {
                GalleryItemDisplay(
                    id = it.toString()
                )
            }
        }

        _isLoading.value = false
    }

    init {
        scope.launch { loadMore() }
    }
}

@Composable
fun GalleryPane(
    modifier: Modifier = Modifier,
    dataSource: PagingSource<GalleryItemDisplay> = GalleryItemDisplayPagingSource(rememberCoroutineScope()),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val isPreview = LocalInspectionMode.current
    val scope = rememberCoroutineScope()
    val items = if (isPreview) (0..10).map { GalleryItemDisplay(id = it.toString()) } else dataSource.items
    val scrollState = rememberScrollState()

    FlowLayout(
        modifier = modifier,
        contentPadding = contentPadding,
        scrollState = scrollState,
        verticalGap = 16.dp,
        horizontalGap = 16.dp,
        enterTransition = slideInVertically { it }
    ) {
        items.forEach { item ->
            item(item, ratio = listOf(2 / 3f, 3 / 2f, 1 / 2f, 2 / 1f).random()) {
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = .3f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (isPreview)
                            Image(
                                painter = painterResource(Res.drawable.compose_multiplatform),
                                contentDescription = null
                            )
                        else
                            AsyncImage(
                                model = item.imageUrls.firstOrNull(),
                                contentDescription = item.imageUrls.first(),
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                    }
                }
            }
        }

        if (dataSource.isCanLoadMore.value)
            item(Unit, 2 / 3f) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    scope.launch { dataSource.loadMore() }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
    }
}

@Preview
@Composable
private fun Preview() {
    GalleryPane(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()

    )
}