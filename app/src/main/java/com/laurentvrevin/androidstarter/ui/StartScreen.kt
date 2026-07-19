package com.laurentvrevin.androidstarter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.laurentvrevin.androidstarter.designsystem.components.button.AppPrimaryButton
import com.laurentvrevin.androidstarter.designsystem.components.button.AppSecondaryButton
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme

@Composable
fun StartScreen(
    onNavigateToShowcase: () -> Unit,
    onNavigateToTemplate: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppTheme.colors.background,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(AppTheme.spacing.standard),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Welcome to AndroidStarter 🚀",
                style = AppTheme.typography.display,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))

            Text(
                text = "This boilerplate is ready to use. Explore the features below to see what's included.",
                style = AppTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.tripleLarge))

            AppPrimaryButton(
                text = "Template Feature (Room + MVVM)",
                onClick = onNavigateToTemplate,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.standard))

            AppSecondaryButton(
                text = "Design System Showcase",
                onClick = onNavigateToShowcase,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
