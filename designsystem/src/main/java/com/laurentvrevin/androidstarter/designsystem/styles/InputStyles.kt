package com.laurentvrevin.androidstarter.designsystem.styles

import androidx.compose.foundation.style.*
import androidx.compose.runtime.Composable
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme

/**
 * Interface définissant les styles pour les inputs.
 */
interface InputStyles {
    @Composable fun default(): Style
}

/**
 * Implémentation par défaut des styles d'inputs.
 */
class DefaultInputStyles : InputStyles {

    @Composable
    override fun default(): Style {
        val spacing = AppTheme.spacing
        val typography = AppTheme.typography
        val colors = AppTheme.colors

        return Style {
            // Ici on peut définir des propriétés globales si besoin
            // mais l'OutlinedTextField de M3 a déjà sa propre logique de style interne.
            // On peut toutefois styler le conteneur ou les labels.
        }
    }
}
