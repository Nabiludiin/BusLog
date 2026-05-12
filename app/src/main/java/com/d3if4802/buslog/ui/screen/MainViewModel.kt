package com.d3if4802.buslog.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if4802.buslog.database.TiketDao
import com.d3if4802.buslog.model.ProfileEntity
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

    val profileData: StateFlow<ProfileEntity?> = dao.getProfile().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
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

    fun insertTiket(tiket: TiketBus) = viewModelScope.launch {
        dao.insertTiket(tiket)
    }
    fun updateTiket(tiket: TiketBus) = viewModelScope.launch {
        dao.updateTiket(tiket)
    }
    fun deleteTiket(tiket: TiketBus) = viewModelScope.launch {
        dao.deleteTiket(tiket)
    }

    fun upsertProfile(nama: String, email: String, nim: String) = viewModelScope.launch {
        val current = profileData.value
        if (current == null) {
            dao.insertProfile(ProfileEntity(id = 1, nama = nama, email = email, nim = nim))
        } else {
            dao.updateProfile(ProfileEntity(id = 1, nama = nama, email = email, nim = nim))
        }
    }

    fun saveLayoutPreference(isGrid: Boolean) = viewModelScope.launch { dataStore.saveLayoutPreference(isGrid) }
    fun saveThemePreference(isDark: Boolean) = viewModelScope.launch { dataStore.saveThemePreference(isDark) }
}