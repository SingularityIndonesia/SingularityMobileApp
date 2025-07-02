package pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
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

@Preview
@Composable
fun GalleryPane(
    modifier: Modifier = Modifier,
    dataSource: PagingSource<GalleryItemDisplay> = GalleryItemDisplayPagingSource(rememberCoroutineScope()),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val scope = rememberCoroutineScope()
    val items = dataSource.items

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item ${item.id}")
                }
            }
        }
        if (dataSource.isCanLoadMore.value)
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                scope.launch {
                    dataSource.loadMore()
                }
            }
    }
}