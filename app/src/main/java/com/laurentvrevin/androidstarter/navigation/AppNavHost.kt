package com.laurentvrevin.androidstarter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laurentvrevin.androidstarter.designsystem.ShowcaseScreen
import com.laurentvrevin.androidstarter.feature.sample.SampleScreen
import kotlinx.serialization.Serializable

@Serializable
data object ShowcaseRoute

@Serializable
data object SampleRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ShowcaseRoute,
        modifier = modifier
    ) {
        composable<ShowcaseRoute> {
            ShowcaseScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle,
                onNavigateToSample = {
                    navController.navigate(SampleRoute)
                }
            )
        }

        composable<SampleRoute> {
            SampleScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
