package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.d3if4802.buslog.database.TiketDb
import com.d3if4802.buslog.util.SettingsDataStore
import com.d3if4802.buslog.util.ViewModelFactory
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = TiketDb.getInstance(context)
    val dataStore = SettingsDataStore(context)
    val factory = ViewModelFactory(db.dao, dataStore)
    val viewModel: MainViewModel = viewModel(factory = factory)

    val tiketList by viewModel.tiketData.collectAsState()
    val isGridView by viewModel.isGridView.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Journeys") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Ticket")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
        }
    }
}