/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * note: please write the system token `as is`, event if it sounds redundant.
 * ex: color/text/text-primary into colorTextTextPrimary.
 */
@Deprecated(
    "Do not call this directly!",
    replaceWith = ReplaceWith(
        "DesignToken.current",
        "core.ui.designsystem.DesignToken",
    ),
)
data class SystemToken(
    val color: ColorScheme
)


val DesignToken = staticCompositionLocalOf {
    @Suppress("DEPRECATION")
    SystemToken(LightThemePalette)
}
