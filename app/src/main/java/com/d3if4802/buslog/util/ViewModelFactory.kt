package com.d3if4802.buslog.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if4802.buslog.database.TiketDao

class ViewModelFactory(
    private val dao: TiketDao,
    private val dataStore: SettingsDataStore
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}