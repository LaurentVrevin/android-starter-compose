package com.laurentvrevin.androidstarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.laurentvrevin.androidstarter.designsystem.theme.AppTheme
import com.laurentvrevin.androidstarter.navigation.AppNavHost
import com.laurentvrevin.androidstarter.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkThemePref by viewModel.isDarkTheme.collectAsState()
            val isDarkTheme = isDarkThemePref ?: isSystemInDarkTheme()
            
            AppTheme(darkTheme = isDarkTheme) {
                AppNavHost(
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = { viewModel.toggleTheme(!isDarkTheme) }
                )
            }
        }
    }
}
