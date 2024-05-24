package main.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import system.core.lifecycle.SaveAbleState

@Immutable
data class HomeScreenPLD(
    val unit: Unit = Unit
)

@Immutable
data class HomeScreenState(
    val unit: Unit = Unit
)


@Composable
fun HomeScreen(
    pld: HomeScreenPLD,
    saveAbleState: SaveAbleState
) {
    
}