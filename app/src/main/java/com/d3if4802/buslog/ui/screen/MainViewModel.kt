package com.d3if4802.buslog.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if4802.buslog.database.TiketDao
import com.d3if4802.buslog.model.TiketBus
import com.d3if4802.buslog.util.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val dao: TiketDao,
    private val dataStore: SettingsDataStore
) : ViewModel() {

    val tiketData: StateFlow<List<TiketBus>> = dao.getAllTiket().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val isGridView: StateFlow<Boolean> = dataStore.layoutFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    val isDarkMode: StateFlow<Boolean> = dataStore.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    fun saveLayoutPreference(isGrid: Boolean) {
        viewModelScope.launch {
            dataStore.saveLayoutPreference(isGrid)
        }
    }

    fun saveThemePreference(isDark: Boolean) {
        viewModelScope.launch {
            dataStore.saveThemePreference(isDark)
        }
    }
}