package com.d3if4802.buslog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d3if4802.buslog.ui.screen.HomeScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Form : Screen("form")
    data object Settings : Screen("settings")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}