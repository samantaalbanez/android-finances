package br.com.finances.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.finances.data.repository.SettingsRepository

class VMSettingsFactory(private val repository: SettingsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMSettings::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VMSettings(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
