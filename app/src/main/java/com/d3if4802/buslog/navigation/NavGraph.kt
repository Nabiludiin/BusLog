package com.d3if4802.buslog.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Settings : Screen("settings")
    data object Form : Screen("form")

    fun withId(id: Int): String = "form/$id"
}