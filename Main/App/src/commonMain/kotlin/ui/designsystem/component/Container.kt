///*
// * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
// * You are not allowed to remove the copyright. Unless you have a "free software" licence.
// */
//package core.ui.designsystem.component
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.awaitEachGesture
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.contentColorFor
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.input.pointer.PointerEvent
//import androidx.compose.ui.input.pointer.PointerInputChange
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import core.ui.SingularityScope
//
//context(SingularityScope)
//@Composable
//fun SSurface(
//    onClick: () -> Unit = {},
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    shape: Shape = RectangleShape,
//    color: Color = MaterialTheme.colorScheme.surface,
//    contentColor: Color = contentColorFor(color),
//    tonalElevation: Dp = 0.dp,
//    shadowElevation: Dp = 0.dp,
//    border: BorderStroke? = null,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    content: @Composable () -> Unit,
//) {
//    Surface(
//        modifier =
//            Modifier
//                .clickable(
//                    interactionSource = interactionSource,
//                    enabled = true,
//                    indication = null,
//                    onClick = {
//                        log("user clicking surface")
//                        onClick.invoke()
//                    },
//                ).pointerInput(Unit) {
//                    awaitEachGesture {
//                        awaitPointerEvent()
//                        // ACTION_DOWN here
//
//                        do {
//                            // This PointerEvent contains details including
//                            // event, id, position and more
//                            val event: PointerEvent = awaitPointerEvent()
//                            // ACTION_MOVE loop
//
//                            // Consuming event prevents other gestures or scroll to intercept
//                            event.changes.forEach { pointerInputChange: PointerInputChange ->
//                                // pointerInputChange.consumePositionChange()
//                                log("User touching the screen x: ${pointerInputChange.position.x}, y: ${pointerInputChange.position.y}")
//                            }
//                        } while (event.changes.any { it.pressed })
//                    }
//                },
//        shape = shape,
//        color = color,
//        contentColor = contentColor,
//        tonalElevation = tonalElevation,
//        shadowElevation = shadowElevation,
//        border = border,
//        content = content,
//    )
//}
