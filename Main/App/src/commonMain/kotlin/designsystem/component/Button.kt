///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ButtonElevation
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.IconButtonColors
//import androidx.compose.material3.IconButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import core.ui.SingularityScope
//
////context(SingularityScope)
////@Composable
////fun SPrimaryButton(
////    modifier: Modifier = Modifier,
////    onClick: () -> Unit,
////    enabled: Boolean = true,
////    /*shape: Shape = ButtonDefaults.shape,*/
////    /*colors: ButtonColors = ButtonDefaults.buttonColors(),*/
////    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
////    /*border: BorderStroke? = null,*/
////    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
////    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
////    isLoading: Boolean = false,
////    content: @Composable RowScope.() -> Unit,
////) {
////
////    Button(
////        modifier = modifier,
////        onClick = {
////            log("Button $content clicked")
////            if (!isLoading)
////                onClick.invoke()
////        },
////        enabled = enabled,
////        shape = ButtonDefaults.shape,
////        colors = ButtonDefaults.buttonColors(),
////        elevation = elevation,
////        border = null,
////        contentPadding = contentPadding,
////        interactionSource = interactionSource
////    ) {
////        if (isLoading)
////            CircularProgressIndicator(
////                modifier = Modifier
////                    .size(24.dp),
////                color = MaterialTheme.colorScheme.onPrimary,
////            )
////        else
////            content()
////    }
////}
////
////context(SingularityScope)
////@Composable
////fun SSecondaryButton(
////    modifier: Modifier = Modifier,
////    onClick: () -> Unit,
////    enabled: Boolean = true,
////    /*shape: Shape = ButtonDefaults.shape,*/
////    /*colors: ButtonColors = ButtonDefaults.buttonColors().copy(
////        containerColor = MaterialTheme.colorScheme.secondary,
////        contentColor = MaterialTheme.colorScheme.onSecondary
////    ),*/
////    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
////    /*border: BorderStroke? = null,*/
////    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
////    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
////    isLoading: Boolean = false,
////    content: @Composable RowScope.() -> Unit,
////) {
////
////    Button(
////        modifier = modifier,
////        onClick = {
////            log("Button $content clicked")
////            if (!isLoading)
////                onClick.invoke()
////        },
////        enabled = enabled,
////        shape = ButtonDefaults.shape,
////        colors = ButtonDefaults.buttonColors().copy(
////            containerColor = MaterialTheme.colorScheme.secondary,
////            contentColor = MaterialTheme.colorScheme.onSecondary
////        ),
////        elevation = elevation,
////        border = null,
////        contentPadding = contentPadding,
////        interactionSource = interactionSource
////    ) {
////        if (isLoading)
////            CircularProgressIndicator(
////                modifier = Modifier
////                    .size(24.dp),
////                color = MaterialTheme.colorScheme.onPrimary,
////            )
////        else
////            content()
////    }
////}
////
////context(SingularityScope)
////@Composable
////fun STertiaryButton(
////    modifier: Modifier = Modifier,
////    onClick: () -> Unit,
////    enabled: Boolean = true,
////    /*shape: Shape = ButtonDefaults.shape,*/
////    /*colors: ButtonColors = ButtonDefaults.buttonColors().copy(
////        containerColor = MaterialTheme.colorScheme.tertiary,
////        contentColor = MaterialTheme.colorScheme.onTertiary
////    ),*/
////    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
////    /*border: BorderStroke? = null,*/
////    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
////    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
////    isLoading: Boolean = false,
////    content: @Composable RowScope.() -> Unit,
////) {
////
////    Button(
////        modifier = modifier,
////        onClick = {
////            log("Button $content clicked")
////            if (!isLoading)
////                onClick.invoke()
////        },
////        enabled = enabled,
////        shape = ButtonDefaults.shape,
////        colors = ButtonDefaults.buttonColors().copy(
////            containerColor = MaterialTheme.colorScheme.tertiary,
////            contentColor = MaterialTheme.colorScheme.onTertiary
////        ),
////        elevation = elevation,
////        border = null,
////        contentPadding = contentPadding,
////        interactionSource = interactionSource
////    ) {
////        if (isLoading)
////            CircularProgressIndicator(
////                modifier = Modifier
////                    .size(24.dp),
////                color = MaterialTheme.colorScheme.onPrimary,
////            )
////        else
////            content()
////    }
////}
////
////context(SingularityScope)
////@Composable
////fun SIconButton(
////    onClick: () -> Unit,
////    modifier: Modifier = Modifier,
////    enabled: Boolean = true,
////    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
////    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
////    content: @Composable () -> Unit
////) {
////    IconButton(
////        onClick = {
////            log("Button clicked $content")
////            onClick()
////        },
////        modifier = modifier,
////        enabled = enabled,
////        colors = colors,
////        interactionSource = interactionSource,
////        content = content,
////    )
////}