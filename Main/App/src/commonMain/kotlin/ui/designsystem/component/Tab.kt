//package core.ui.designsystem.component
//
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.ColumnScope
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.LocalContentColor
//import androidx.compose.material3.Tab
//import androidx.compose.material3.TabPosition
//import androidx.compose.material3.TabRow
//import androidx.compose.material3.TabRowDefaults
//import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import core.ui.SingularityScope
//
////context(SingularityScope)
////@Composable
////fun STabRow(
////    selectedTabIndex: Int,
////    modifier: Modifier = Modifier,
////    containerColor: Color = TabRowDefaults.primaryContainerColor,
////    contentColor: Color = TabRowDefaults.primaryContentColor,
////    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
////        if (selectedTabIndex < tabPositions.size) {
////            TabRowDefaults.SecondaryIndicator(
////                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
////            )
////        }
////    },
////    divider: @Composable () -> Unit = @Composable {
////        HorizontalDivider()
////    },
////    tabs: @Composable () -> Unit
////) {
////    TabRow(
////        selectedTabIndex = selectedTabIndex,
////        modifier = modifier,
////        containerColor = containerColor,
////        contentColor = contentColor,
////        indicator = indicator,
////        divider = divider,
////        tabs = tabs
////    )
////
////    LaunchedEffect(selectedTabIndex) {
////        log("Tab focus on $selectedTabIndex")
////    }
////}
////
////context(SingularityScope)
////@Composable
////fun STab(
////    selected: Boolean,
////    onClick: () -> Unit,
////    modifier: Modifier = Modifier,
////    enabled: Boolean = true,
////    selectedContentColor: Color = LocalContentColor.current,
////    unselectedContentColor: Color = selectedContentColor,
////    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
////    content: @Composable ColumnScope.() -> Unit
////) {
////    Tab(
////        selected = selected,
////        onClick = {
////            log("Tab clicked $content")
////            onClick()
////        },
////        modifier = modifier,
////        enabled = enabled,
////        selectedContentColor = selectedContentColor,
////        unselectedContentColor = unselectedContentColor,
////        interactionSource = interactionSource,
////        content = content,
////    )
////}