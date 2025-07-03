///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.ColumnScope
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardColors
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CardElevation
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import core.ui.SingularityScope
//
//context(SingularityScope)
//@Composable
//fun SSmallCard(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    /*shape: Shape = CardDefaults.shape,*/
//    colors: CardColors = CardDefaults.cardColors(),
//    elevation: CardElevation = CardDefaults.cardElevation(),
//    border: BorderStroke? = null,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Card(
//        onClick = {
//            log("Card is Clicked $content")
//            onClick()
//        },
//        modifier = modifier,
//        enabled = enabled,
//        shape = RoundedCornerShape(8.dp),
//        colors = colors,
//        elevation = elevation,
//        border = border,
//        interactionSource = interactionSource,
//        content = content,
//    )
//}
//
//context(SingularityScope)
//@Composable
//fun SMediumCard(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    /*shape: Shape = CardDefaults.shape,*/
//    colors: CardColors = CardDefaults.cardColors(),
//    elevation: CardElevation = CardDefaults.cardElevation(),
//    border: BorderStroke? = null,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Card(
//        onClick = {
//            log("Card is Clicked $content")
//            onClick()
//        },
//        modifier = modifier,
//        enabled = enabled,
//        shape = RoundedCornerShape(16.dp),
//        colors = colors,
//        elevation = elevation,
//        border = border,
//        interactionSource = interactionSource,
//        content = content,
//    )
//}
//
//context(SingularityScope)
//@Composable
//fun SLargeCard(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    /*shape: Shape = CardDefaults.shape,*/
//    colors: CardColors = CardDefaults.cardColors(),
//    elevation: CardElevation = CardDefaults.cardElevation(),
//    border: BorderStroke? = null,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Card(
//        onClick = {
//            log("Card is Clicked $content")
//            onClick()
//        },
//        modifier = modifier,
//        enabled = enabled,
//        shape = RoundedCornerShape(24.dp),
//        colors = colors,
//        elevation = elevation,
//        border = border,
//        interactionSource = interactionSource,
//        content = content,
//    )
//}