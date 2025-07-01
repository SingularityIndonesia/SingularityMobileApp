package pane

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import navigation.HomeDestinationBuilder
import navigation.HomeSection
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.currentDestinationArgument

@Preview
@Composable
fun HomePane(
    navController: NavHostController = rememberNavController()
) {
    val argument by currentDestinationArgument(navController)
    val pagerState = rememberPagerState(0) { 4 }
    val intendedSection = remember(argument) {
        argument
            ?.getString("section", HomeSection.Universe.name)
            ?.let { HomeSection.valueOf(it) }
            ?: HomeSection.Universe
    }

    // Scroll to intended section
    LaunchedEffect(intendedSection) {
        check(!pagerState.isScrollInProgress) {
            return@LaunchedEffect
        }

        check(pagerState.currentPage != intendedSection.ordinal) {
            return@LaunchedEffect
        }

        pagerState.animateScrollToPage(intendedSection.ordinal)
    }

    // Bind Pager State to route
    LaunchedEffect(
        pagerState.currentPage,
        pagerState.isScrollInProgress,
        intendedSection
    ) {
        check(!pagerState.isScrollInProgress) {
            return@LaunchedEffect
        }

        check(pagerState.currentPage != intendedSection.ordinal) {
            return@LaunchedEffect
        }

        navController.navigate(
            HomeDestinationBuilder(section = HomeSection.entries[pagerState.currentPage])
        ) {
            launchSingleTop = true
        }
    }

    HorizontalPager(
        state = pagerState
    ) { pageIndex ->
        when (pageIndex) {
            0 -> Universe(
                modifier = Modifier.fillMaxSize()
            )

            1 -> Text(
                modifier = Modifier.fillMaxSize(),
                text = "Page1"
            )

            2 -> Text(
                modifier = Modifier.fillMaxSize(),
                text = "Page2"
            )

            3 -> Text(
                modifier = Modifier.fillMaxSize(),
                text = "Page3"
            )
        }
    }
}