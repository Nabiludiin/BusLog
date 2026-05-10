package com.d3if4802.buslog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.d3if4802.buslog.database.TiketDb
import com.d3if4802.buslog.ui.screen.MainScreen
import com.d3if4802.buslog.ui.screen.MainViewModel
import com.d3if4802.buslog.ui.theme.BusLogTheme
import com.d3if4802.buslog.util.SettingsDataStore
import com.d3if4802.buslog.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = androidx.compose.ui.platform.LocalContext.current
            val db = TiketDb.getInstance(context)
            val dataStore = SettingsDataStore(context)
            val factory = ViewModelFactory(db.dao, dataStore)
            val viewModel: MainViewModel = viewModel(factory = factory)

            val isDark by viewModel.isDarkMode.collectAsState()

            BusLogTheme(darkTheme = isDark) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainScreen(navController = navController)
                }
            }
        }
    }
}