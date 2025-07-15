/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package ui.designsystem.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import font.resources.*
import org.jetbrains.compose.resources.Font

// PT Serif FontFamily using resources
@Composable
fun PTSerifFontFamily() = FontFamily(
    Font(
        resource = Res.font.PTSerif_Regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.PTSerif_Bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = Res.font.PTSerif_Italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.PTSerif_BoldItalic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    )
)
