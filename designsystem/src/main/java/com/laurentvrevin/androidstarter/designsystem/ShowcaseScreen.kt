package com.laurentvrevin.androidstarter.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laurentvrevin.androidstarter.designsystem.components.button.*
import com.laurentvrevin.androidstarter.designsystem.components.card.AppCard
import com.laurentvrevin.androidstarter.designsystem.components.chip.AppChip
import com.laurentvrevin.androidstarter.designsystem.components.feedback.AppSnackbar
import com.laurentvrevin.androidstarter.designsystem.components.feedback.EmptyState
import com.laurentvrevin.androidstarter.designsystem.components.feedback.LoadingOverlay
import com.laurentvrevin.androidstarter.designsystem.components.input.AppInput
import com.laurentvrevin.androidstarter.designsystem.components.topbar.AppTopBar
import com.laurentvrevin.androidstarter.designsystem.foundation.AppSize
import com.laurentvrevin.androidstarter.designsystem.patterns.SectionBlock
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme
import com.laurentvrevin.androidstarter.designsystem.ui.SnackbarType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowcaseScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    onNavigateToTemplate: () -> Unit = {},
) {
    val spacing = AppTheme.spacing
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var currentSnackbarType by remember { mutableStateOf(SnackbarType.Default) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Design System Showcase",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                AppSnackbar(snackbarData = data, type = currentSnackbarType)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onThemeToggle) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Toggle Theme",
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(spacing.standard),
            verticalArrangement = Arrangement.spacedBy(spacing.doubleLarge),
        ) {
            // SECTION: TYPOGRAPHY
            SectionBlock(title = "Typography") {
                Text("Display Large", style = typography.display)
                Text("Headline 1", style = typography.h1)
                Text("Headline 2", style = typography.h2)
                Text("Title Large", style = typography.titleLarge)
                Text("Body Large (Default)", style = typography.bodyLarge)
                Text("Body Small", style = typography.bodySmall)
                Text("Label Medium", style = typography.labelMedium)
            }

            // SECTION: COLORS
            SectionBlock(title = "Core Colors") {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    ColorBox("Primary", colors.primary, colors.onPrimary, Modifier.weight(1f))
                    ColorBox("Secondary", colors.secondary, colors.onSecondary, Modifier.weight(1f))
                    ColorBox("Error", colors.error, colors.onError, Modifier.weight(1f))
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    ColorBox("Surface", colors.surface, colors.onSurface, Modifier.weight(1f))
                    ColorBox("Variant", colors.surfaceVariant, colors.onSurfaceVariant, Modifier.weight(1f))
                    ColorBox("Outline", colors.outline, colors.onSurface, Modifier.weight(1f))
                }
            }

            // SECTION: FOUNDATIONS
            SectionBlock(title = "Foundations") {
                Text("Shapes", style = typography.titleLarge)
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    Box(Modifier.size(60.dp).clip(shapes.small).background(colors.primaryContainer))
                    Box(Modifier.size(60.dp).clip(shapes.medium).background(colors.primaryContainer))
                    Box(Modifier.size(60.dp).clip(shapes.large).background(colors.primaryContainer))
                    Box(Modifier.size(60.dp).clip(shapes.pill).background(colors.primaryContainer))
                }

                Spacer(Modifier.height(spacing.small))
                Text("Spacing Scale", style = typography.titleLarge)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    SpacingBar("XS", spacing.extraSmall, colors.secondary)
                    SpacingBar("S", spacing.small, colors.secondary)
                    SpacingBar("M", spacing.medium, colors.secondary)
                    SpacingBar("Std", spacing.standard, colors.secondary)
                    SpacingBar("L", spacing.large, colors.secondary)
                }
            }

            // SECTION: BUTTONS
            SectionBlock(title = "Buttons") {
                Text("Variantes (Medium)", style = typography.bodySmall)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    verticalArrangement = Arrangement.spacedBy(spacing.small),
                ) {
                    AppPrimaryButton("Primary", onClick = {})
                    AppSecondaryButton("Secondary", onClick = {})
                    AppOutlinedButton("Outlined", onClick = {})
                    AppGhostButton("Ghost", onClick = {})
                    AppDangerButton("Danger", onClick = {})
                }

                Text("Tailles (Primary)", style = typography.bodySmall)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                ) {
                    AppPrimaryButton("Small", size = AppSize.Small, onClick = {})
                    AppPrimaryButton("Medium", size = AppSize.Medium, onClick = {})
                    AppPrimaryButton("Large", size = AppSize.Large, onClick = {})
                }
            }

            // SECTION: CHIPS
            SectionBlock(title = "Chips") {
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    AppChip("Small", size = AppSize.Small)
                    AppChip("Medium", size = AppSize.Medium)
                    AppChip("Selected", selected = true)
                }
            }

            // SECTION: INPUTS
            SectionBlock(title = "Inputs") {
                var text by remember { mutableStateOf("") }
                AppInput(
                    value = text,
                    onValueChange = { text = it },
                    label = "Default Input",
                    modifier = Modifier.fillMaxWidth(),
                )
                AppInput(
                    value = "Invalid content",
                    onValueChange = {},
                    label = "Error Input",
                    isError = true,
                    errorMessage = "This field is required",
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            // SECTION: CARDS
            SectionBlock(title = "Cards") {
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Text("Default Card with Shadow Level 1", style = typography.bodyLarge)
                }
                AppCard(
                    modifier = Modifier.fillMaxWidth(),
                    style = AppTheme.cardStyles.elevated(),
                ) {
                    Text("Elevated Card with Shadow Level 2", style = typography.bodyLarge)
                }
                AppCard(
                    modifier = Modifier.fillMaxWidth(),
                    style = AppTheme.cardStyles.outlined(),
                ) {
                    Text("Outlined Card", style = typography.bodyLarge)
                }
            }

            // SECTION: UI STATE & FEEDBACK
            SectionBlock(title = "Navigation & Templates") {
                AppPrimaryButton(
                    text = "Go to Template Feature",
                    onClick = onNavigateToTemplate,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            SectionBlock(title = "UI State & Feedback") {
                var showGlobalLoader by remember { mutableStateOf(false) }

                Text("Snackbars", style = typography.titleLarge)
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    AppPrimaryButton("Default", onClick = {
                        scope.launch {
                            currentSnackbarType = SnackbarType.Default
                            snackbarHostState.showSnackbar("This is a default message")
                        }
                    }, size = AppSize.Small)

                    AppPrimaryButton("Success", onClick = {
                        scope.launch {
                            currentSnackbarType = SnackbarType.Success
                            snackbarHostState.showSnackbar("Operation successful!")
                        }
                    }, size = AppSize.Small)

                    AppDangerButton("Error", onClick = {
                        scope.launch {
                            currentSnackbarType = SnackbarType.Error
                            snackbarHostState.showSnackbar("An error occurred")
                        }
                    }, size = AppSize.Small)
                }

                Spacer(Modifier.height(spacing.small))
                Text("Loading States", style = typography.titleLarge)
                AppSecondaryButton("Trigger Fullscreen Loader (2s)", onClick = {
                    scope.launch {
                        showGlobalLoader = true
                        delay(2000)
                        showGlobalLoader = false
                    }
                })

                Spacer(Modifier.height(spacing.small))
                Text("Empty States", style = typography.titleLarge)
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    EmptyState(
                        message = "No items found. Try searching for something else.",
                        action = {
                            AppOutlinedButton("Retry", onClick = {}, size = AppSize.Small)
                        },
                    )
                }

                LoadingOverlay(isLoading = showGlobalLoader)
            }

            Spacer(Modifier.height(spacing.tripleLarge))
        }
    }
}

@Composable
private fun ColorBox(
    label: String,
    color: Color,
    onColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .height(80.dp)
                .clip(AppTheme.shapes.medium)
                .background(color)
                .padding(AppTheme.spacing.extraSmall),
        contentAlignment = Alignment.BottomStart,
    ) {
        Text(label, color = onColor, style = AppTheme.typography.labelMedium)
    }
}

@Composable
private fun SpacingBar(
    label: String,
    width: androidx.compose.ui.unit.Dp,
    color: Color,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label, modifier = Modifier.width(40.dp), style = AppTheme.typography.labelMedium)
        Box(
            Modifier
                .height(12.dp)
                .width(width)
                .background(color),
        )
        Text(" ${width.value.toInt()}dp", style = AppTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowcasePreview() {
    var dark by remember { mutableStateOf(false) }
    AppTheme(darkTheme = dark) {
        ShowcaseScreen(isDarkTheme = dark, onThemeToggle = { dark = !dark })
    }
}
