package com.laurentvrevin.androidstarter.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.laurentvrevin.androidstarter.designsystem.foundation.*
import com.laurentvrevin.androidstarter.designsystem.styles.*

/**
 * Objet d'accès centralisé aux jetons (tokens) et styles du Design System.
 * 
 * Il agit comme un proxy vers les CompositionLocal injectés par [AppTheme].
 * Cette approche permet une syntaxe concise : `AppTheme.spacing.medium`
 * au lieu de `LocalAppSpacing.current.medium`.
 */
object AppTheme {

    val spacing: AppSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalAppSpacing.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val elevation: AppElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalAppElevation.current

    val borders: AppBorders
        @Composable
        @ReadOnlyComposable
        get() = LocalAppBorders.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimensions.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val buttonStyles: ButtonStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalButtonStyles.current

    val cardStyles: CardStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalCardStyles.current

    val chipStyles: ChipStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalChipStyles.current

    val inputStyles: InputStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalInputStyles.current

    val topBarStyles: TopBarStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTopBarStyles.current

    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme
}
