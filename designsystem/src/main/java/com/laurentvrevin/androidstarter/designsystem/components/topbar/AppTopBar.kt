package com.laurentvrevin.androidstarter.designsystem.components.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.style.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.laurentvrevin.androidstarter.designsystem.styles.*
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    style: Style? = null
) {
    val topBarStyle = style ?: AppTheme.topBarStyles.default()
    val typography = AppTheme.typography

    Row(
        modifier = modifier.styleable(style = topBarStyle),
    ) {
        Text(
            text = title,
            style = typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTopBarPreview() {
    AppTheme {
        AppTopBar(title = "Paramètres")
    }
}
