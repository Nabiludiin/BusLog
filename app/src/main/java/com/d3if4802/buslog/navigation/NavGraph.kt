package com.d3if4802.buslog.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Form : Screen("form")
    data object Settings : Screen("settings")
}