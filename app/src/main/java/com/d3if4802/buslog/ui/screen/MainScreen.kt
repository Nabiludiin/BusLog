package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.d3if4802.buslog.R
import com.d3if4802.buslog.navigation.Screen
import com.d3if4802.buslog.navigation.SetupNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.menu_home)) },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true,
                    onClick = {
                        navController.navigate(Screen.Home.route)
                        scope.launch { drawerState.close() }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.menu_settings)) },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Settings.route } == true,
                    onClick = {
                        navController.navigate(Screen.Settings.route)
                        scope.launch { drawerState.close() }
                    },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text(stringResource(R.string.menu_home)) },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true,
                        onClick = { navController.navigate(Screen.Home.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = null) },
                        label = { Text(stringResource(R.string.menu_profile)) },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.Profile.route } == true,
                        onClick = { navController.navigate(Screen.Profile.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        label = { Text(stringResource(R.string.menu_settings)) },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.Settings.route } == true,
                        onClick = { navController.navigate(Screen.Settings.route) }
                    )
                }
            },
            floatingActionButton = {
                if (currentDestination?.route == Screen.Home.route) {
                    FloatingActionButton(onClick = { navController.navigate(Screen.Form.route) }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                SetupNavGraph(navController = navController)
            }
        }
    }
}
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    com.d3if4802.buslog.ui.theme.BusLogTheme {
        MainScreen(navController)
    }
}