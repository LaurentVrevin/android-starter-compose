package com.laurentvrevin.androidstarter.designsystem.styles

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocals permettant d'accéder aux fournisseurs de styles dans l'arborescence UI.
 */

val LocalButtonStyles = staticCompositionLocalOf<ButtonStyles> {
    error("No ButtonStyles provided")
}

val LocalCardStyles = staticCompositionLocalOf<CardStyles> {
    error("No CardStyles provided")
}

val LocalChipStyles = staticCompositionLocalOf<ChipStyles> {
    error("No ChipStyles provided")
}

val LocalInputStyles = staticCompositionLocalOf<InputStyles> {
    error("No InputStyles provided")
}

val LocalTopBarStyles = staticCompositionLocalOf<TopBarStyles> {
    error("No TopBarStyles provided")
}
