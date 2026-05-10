package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d3if4802.buslog.R
import com.d3if4802.buslog.database.TiketDb
import com.d3if4802.buslog.util.SettingsDataStore
import com.d3if4802.buslog.util.ViewModelFactory

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val db = TiketDb.getInstance(context)
    val dataStore = SettingsDataStore(context)
    val factory = ViewModelFactory(db.dao, dataStore)
    val viewModel: MainViewModel = viewModel(factory = factory)

    val isGridView by viewModel.isGridView.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = stringResource(R.string.grid_layout), style = MaterialTheme.typography.bodyLarge)
                Text(text = stringResource(R.string.grid_desc), style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Switch(checked = isGridView, onCheckedChange = { viewModel.saveLayoutPreference(it) })
        }
        HorizontalDivider(thickness = 0.5.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = stringResource(R.string.dark_mode), style = MaterialTheme.typography.bodyLarge)
                Text(text = stringResource(R.string.dark_desc), style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Switch(checked = isDarkMode, onCheckedChange = { viewModel.saveThemePreference(it) })
        }
    }
}
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    com.d3if4802.buslog.ui.theme.BusLogTheme {
        SettingsScreen()
    }
}