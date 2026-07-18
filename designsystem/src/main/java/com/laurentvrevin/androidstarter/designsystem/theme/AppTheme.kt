package com.laurentvrevin.androidstarter.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.laurentvrevin.androidstarter.designsystem.foundation.*
import com.laurentvrevin.androidstarter.designsystem.styles.*

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = AppDarkColors.primary,
            primaryContainer = AppDarkColors.primaryContainer,
            secondary = AppDarkColors.secondary,
            background = AppDarkColors.background,
            surface = AppDarkColors.surface,
            surfaceVariant = AppDarkColors.surfaceVariant,
            onPrimary = AppDarkColors.onPrimary,
            onBackground = AppDarkColors.onBackground,
            onSurface = AppDarkColors.onSurface,
            onSurfaceVariant = AppDarkColors.onSurfaceVariant,
            outline = AppDarkColors.outline,
            error = AppDarkColors.error,
            scrim = AppDarkColors.scrim
        )
    } else {
        lightColorScheme(
            primary = AppLightColors.primary,
            primaryContainer = AppLightColors.primaryContainer,
            secondary = AppLightColors.secondary,
            background = AppLightColors.background,
            surface = AppLightColors.surface,
            surfaceVariant = AppLightColors.surfaceVariant,
            onPrimary = AppLightColors.onPrimary,
            onBackground = AppLightColors.onBackground,
            onSurface = AppLightColors.onSurface,
            onSurfaceVariant = AppLightColors.onSurfaceVariant,
            outline = AppLightColors.outline,
            error = AppLightColors.error,
            scrim = AppLightColors.scrim
        )
    }

    val appTypography = AppTypography()
    val m3Typography = Typography(
        displayLarge = appTypography.display,
        headlineLarge = appTypography.h1,
        headlineMedium = appTypography.h2,
        titleLarge = appTypography.titleLarge,
        bodyLarge = appTypography.bodyLarge,
        bodyMedium = appTypography.bodySmall,
        labelMedium = appTypography.labelMedium
    )

    CompositionLocalProvider(
        LocalButtonStyles provides DefaultButtonStyles(),
        LocalCardStyles provides DefaultCardStyles(),
        LocalChipStyles provides DefaultChipStyles(),
        LocalInputStyles provides DefaultInputStyles(),
        LocalTopBarStyles provides DefaultTopBarStyles(),
        LocalAppSpacing provides AppSpacing(),
        LocalAppShapes provides AppShapes(),
        LocalAppElevation provides AppElevation(),
        LocalAppBorders provides AppBorders(),
        LocalAppDimensions provides AppDimensions(),
        LocalAppTypography provides appTypography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = m3Typography,
            content = content
        )
    }
}
