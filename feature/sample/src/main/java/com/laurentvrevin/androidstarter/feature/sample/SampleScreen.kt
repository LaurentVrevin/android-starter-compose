package com.laurentvrevin.androidstarter.feature.sample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laurentvrevin.androidstarter.core.model.SampleItem
import com.laurentvrevin.androidstarter.designsystem.components.button.AppPrimaryButton
import com.laurentvrevin.androidstarter.designsystem.components.card.AppCard
import com.laurentvrevin.androidstarter.designsystem.components.feedback.EmptyState
import com.laurentvrevin.androidstarter.designsystem.components.feedback.LoadingOverlay
import com.laurentvrevin.androidstarter.designsystem.components.topbar.AppTopBar
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SampleScreen(
    onBack: () -> Unit,
    viewModel: SampleViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Sample Feature",
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (state.items.isEmpty() && !state.isLoading) {
                EmptyState(
                    message = "Aucune donnée disponible. Tentez de rafraîchir.",
                    action = {
                        AppPrimaryButton("Rafraîchir", onClick = viewModel::refresh)
                    }
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(AppTheme.spacing.standard),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.standard)
                ) {
                    items(state.items, key = { it.id }) { item ->
                        SampleItemCard(item)
                    }
                }
            }

            if (state.isRefreshing) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            state.error?.let { error ->
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
                    action = {
                        TextButton(onClick = viewModel::refresh) { Text("RETRY") }
                    }
                ) {
                    Text(error.asString())
                }
            }

            LoadingOverlay(isLoading = state.isLoading)
        }
    }
}

@Composable
private fun SampleItemCard(item: SampleItem) {
    AppCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(AppTheme.spacing.standard)) {
            Text(item.title, style = AppTheme.typography.titleLarge)
            Spacer(Modifier.height(4.dp))
            Text(item.description, style = AppTheme.typography.bodyLarge)
        }
    }
}
