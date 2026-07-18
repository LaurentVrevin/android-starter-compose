package com.laurentvrevin.androidstarter.designsystem.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laurentvrevin.androidstarter.designsystem.styles.*
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    style: Style? = null,
    content: @Composable () -> Unit
) {
    val cardStyle = style ?: LocalCardStyles.current.default()
    
    Column(
        modifier = modifier.styleable(style = cardStyle)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun AppCardPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppCard {
                Text("Default Card Content")
            }
            AppCard(style = LocalCardStyles.current.elevated()) {
                Text("Elevated Card Content")
            }
            AppCard(style = LocalCardStyles.current.outlined()) {
                Text("Outlined Card Content")
            }
        }
    }
}

