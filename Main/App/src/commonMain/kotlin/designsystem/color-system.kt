@file:Suppress("DEPRECATION", "ObjectPropertyName")

package designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val LightThemePalette = lightColorScheme(
    primary = HawkingRadiation[60]!!,
    onPrimary = HawkingRadiation[100]!!,
    primaryContainer = HawkingRadiation[90]!!,
    onPrimaryContainer = HawkingRadiation[10]!!,

    secondary = HawkingDimRadiation[40]!!,
    onSecondary = HawkingDimRadiation[100]!!,
    secondaryContainer = HawkingDimRadiation[90]!!,
    onSecondaryContainer = HawkingDimRadiation[10]!!,

    tertiary = HighEnergyPhoton[40]!!,
    onTertiary = HighEnergyPhoton[100]!!,
    tertiaryContainer = HighEnergyPhoton[90]!!,
    onTertiaryContainer = HighEnergyPhoton[10]!!,

    error = Error[40]!!,
    onError = Error[100]!!,
    errorContainer = Error[90]!!,
    onErrorContainer = Error[10]!!,

    surfaceDim = SpaceDark[87]!!,
    surface = SpaceDark[98]!!,
    surfaceBright = SpaceDark[98]!!,
    onSurface = SpaceDark[10]!!,

    surfaceContainerLowest = SpaceDark[100]!!,
    surfaceContainerLow = SpaceDark[96]!!,
    surfaceContainer = SpaceDark[94]!!,
    surfaceContainerHigh = SpaceDark[92]!!,
    surfaceContainerHighest = SpaceDark[90]!!,

    // surfaceVariant = SpaceDarkVariant[90]!!,
    onSurfaceVariant = SpaceDarkVariant[30]!!,

    outline = SpaceDarkVariant[50]!!,
    outlineVariant = SpaceDarkVariant[80]!!,

    inverseSurface = SpaceDark[20]!!,
    inverseOnSurface = SpaceDark[95]!!,
    inversePrimary = HawkingRadiation[80]!!,

    scrim = SpaceDark[0]!!,
)

val DarkThemePalette = darkColorScheme(
    primary = HawkingRadiation[80]!!,
    onPrimary = HawkingRadiation[20]!!,
    primaryContainer = HawkingRadiation[30]!!,
    onPrimaryContainer = HawkingRadiation[90]!!,

    secondary = HawkingDimRadiation[80]!!,
    onSecondary = HawkingDimRadiation[20]!!,
    secondaryContainer = HawkingDimRadiation[30]!!,
    onSecondaryContainer = HawkingDimRadiation[90]!!,

    tertiary = HighEnergyPhoton[80]!!,
    onTertiary = HighEnergyPhoton[20]!!,
    tertiaryContainer = HighEnergyPhoton[30]!!,
    onTertiaryContainer = HighEnergyPhoton[90]!!,

    error = Error[90]!!,
    onError = Error[20]!!,
    errorContainer = Error[30]!!,
    onErrorContainer = Error[90]!!,

    surfaceDim = SpaceDark[6]!!,
    surface = SpaceDark[6]!!,
    surfaceBright = SpaceDark[24]!!,
    onSurface = SpaceDark[90]!!,

    surfaceContainerLowest = SpaceDark[4]!!,
    surfaceContainerLow = SpaceDark[10]!!,
    surfaceContainer = SpaceDark[12]!!,
    surfaceContainerHigh = SpaceDark[17]!!,
    surfaceContainerHighest = SpaceDark[22]!!,

    // surfaceVariant = SpaceDarkVariant[6]!!,
    onSurfaceVariant = SpaceDarkVariant[80]!!,

    outline = SpaceDarkVariant[60]!!,
    outlineVariant = SpaceDarkVariant[30]!!,

    inverseSurface = SpaceDark[90]!!,
    inverseOnSurface = SpaceDark[20]!!,
    inversePrimary = HawkingRadiation[40]!!,

    scrim = SpaceDark[0]!!,
)

val SystemToken.primary get() = HawkingRadiation[60]!!
val SystemToken.`on-primary` get() = HawkingRadiation[100]!!
val SystemToken.`primary-container` get() = HawkingRadiation[90]!!
val SystemToken.`on-primary-container` get() = HawkingRadiation[10]!!
val SystemToken.secondary get() = HawkingDimRadiation[40]!!
val SystemToken.`on-secondary` get() = HawkingDimRadiation[100]!!
val SystemToken.`secondary-container` get() = HawkingDimRadiation[90]!!
val SystemToken.`on-secondary-container` get() = HawkingDimRadiation[10]!!
val SystemToken.tertiary get() = HighEnergyPhoton[40]!!
val SystemToken.`on-tertiary` get() = HighEnergyPhoton[100]!!
val SystemToken.`tertiary-container` get() = HighEnergyPhoton[90]!!
val SystemToken.`on-tertiary-container` get() = HighEnergyPhoton[10]!!
val SystemToken.error get() = Error[40]!!
val SystemToken.`on-error` get() = Error[100]!!
val SystemToken.`error-container` get() = Error[90]!!
val SystemToken.`on-error-container` get() = Error[10]!!
val SystemToken.`surface-dim` get() = SpaceDark[87]!!
val SystemToken.surface get() = SpaceDark[98]!!
val SystemToken.`surface-bright` get() = SpaceDark[98]!!
val SystemToken.`on-surface` get() = SpaceDark[10]!!
val SystemToken.`surface-container-lowest` get() = SpaceDark[100]!!
val SystemToken.`surface-container-low` get() = SpaceDark[96]!!
val SystemToken.`surface-container` get() = SpaceDark[94]!!
val SystemToken.`surface-container-high` get() = SpaceDark[92]!!
val SystemToken.`surface-container-highest` get() = SpaceDark[90]!!
val SystemToken.`surface-variant` get() = SpaceDarkVariant[90]!!
val SystemToken.`on-surface-variant` get() = SpaceDarkVariant[30]!!
val SystemToken.outline get() = SpaceDarkVariant[50]!!
val SystemToken.`outline-variant` get() = SpaceDarkVariant[80]!!
val SystemToken.`inverse-surface` get() = SpaceDark[20]!!
val SystemToken.`inverse-on-surface` get() = SpaceDark[95]!!
val SystemToken.`inverse-primary` get() = HawkingRadiation[80]!!
val SystemToken.scrim get() = SpaceDark[0]!!