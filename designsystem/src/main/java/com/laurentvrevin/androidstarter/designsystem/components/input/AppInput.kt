package com.laurentvrevin.androidstarter.designsystem.components.input

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.style.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.laurentvrevin.androidstarter.designsystem.styles.*
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun AppInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    style: Style? = null,
) {
    val inputStyle = style ?: AppTheme.inputStyles.default()
    val spacing = AppTheme.spacing
    val typography = AppTheme.typography
    val colors = AppTheme.colors

    Column(modifier = modifier.styleable(style = inputStyle)) {
        Text(
            text = label,
            style = typography.bodySmall,
        )

        Spacer(Modifier.height(spacing.extraSmall))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
        )

        if (isError && errorMessage != null) {
            Spacer(Modifier.height(spacing.extraSmall))
            Text(
                text = errorMessage,
                style = typography.labelMedium,
                color = colors.error,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppInputPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(AppTheme.spacing.standard),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.standard),
        ) {
            AppInput(
                value = "",
                onValueChange = {},
                label = "Nom",
            )
            AppInput(
                value = "Laurent",
                onValueChange = {},
                label = "Prénom",
                isError = true,
                errorMessage = "Erreur de saisie",
            )
        }
    }
}
