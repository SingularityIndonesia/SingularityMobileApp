///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.gestures.FlingBehavior
//import androidx.compose.foundation.gestures.ScrollableDefaults
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyListScope
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import core.ui.SingularityScope
//
//
////context(SingularityScope)
////@Composable
////fun SLazyColumn(
////    modifier: Modifier = Modifier,
////    state: LazyListState = rememberLazyListState(),
////    contentPadding: PaddingValues = PaddingValues(0.dp),
////    reverseLayout: Boolean = false,
////    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
////    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
////    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
////    userScrollEnabled: Boolean = true,
////    content: LazyListScope.() -> Unit
////) {
////    LazyColumn(
////        modifier = modifier,
////        state = state,
////        contentPadding = contentPadding,
////        reverseLayout = reverseLayout,
////        verticalArrangement = verticalArrangement,
////        horizontalAlignment = horizontalAlignment,
////        flingBehavior = flingBehavior,
////        userScrollEnabled = userScrollEnabled,
////        content = content,
////    )
////
////    LaunchedEffect(state.firstVisibleItemIndex) {
////        log("user scrolling to index ${state.firstVisibleItemIndex}")
////    }
////}