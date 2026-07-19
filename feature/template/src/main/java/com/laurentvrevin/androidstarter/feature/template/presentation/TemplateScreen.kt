package com.laurentvrevin.androidstarter.feature.template.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laurentvrevin.androidstarter.designsystem.components.button.AppPrimaryButton
import com.laurentvrevin.androidstarter.designsystem.components.card.AppCard
import com.laurentvrevin.androidstarter.designsystem.components.feedback.EmptyState
import com.laurentvrevin.androidstarter.designsystem.components.feedback.LoadingOverlay
import com.laurentvrevin.androidstarter.designsystem.components.topbar.AppTopBar
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme
import com.laurentvrevin.androidstarter.feature.template.domain.TemplateItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun TemplateScreen(
    onBack: () -> Unit,
    viewModel: TemplateViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Boilerplate Template",
                onBackClick = onBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addTemplate("New Item", "Generated at ${System.currentTimeMillis()}")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            if (state.items.isEmpty() && !state.isInitialLoading) {
                EmptyState(
                    message = "No data available in local Room database.",
                    action = {
                        AppPrimaryButton("Add Sample Data", onClick = {
                            viewModel.addTemplate("First Item", "This is stored in Room.")
                        })
                    },
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(AppTheme.spacing.standard),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.standard),
                ) {
                    items(state.items, key = { it.id }) { item ->
                        TemplateItemCard(
                            item = item,
                            onDelete = { viewModel.deleteTemplate(item.id) },
                        )
                    }
                }
            }

            LoadingOverlay(isLoading = state.isInitialLoading)
        }
    }
}

@Composable
private fun TemplateItemCard(
    item: TemplateItem,
    onDelete: () -> Unit,
) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(AppTheme.spacing.standard),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, style = AppTheme.typography.titleLarge)
                Spacer(Modifier.height(4.dp))
                Text(item.description, style = AppTheme.typography.bodyLarge)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = AppTheme.colors.error)
            }
        }
    }
}
