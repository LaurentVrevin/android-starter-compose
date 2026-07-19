package com.laurentvrevin.androidstarter.designsystem.patterns

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme

/**
 * Un bloc de section avec un titre optionnel.
 * Utile pour séparer les contenus dans un écran long.
 */
@Composable
fun SectionBlock(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val spacing = AppTheme.spacing
    val typography = AppTheme.typography

    Column(modifier = modifier.fillMaxWidth()) {
        if (title != null) {
            Text(
                text = title,
                style = typography.titleLarge,
            )
            Spacer(Modifier.height(spacing.medium))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            content = content,
        )
    }
}
