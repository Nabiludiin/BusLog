package com.d3if4802.buslog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d3if4802.buslog.ui.screen.HomeScreen
import com.d3if4802.buslog.ui.screen.SettingsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController)
        }
    }
}