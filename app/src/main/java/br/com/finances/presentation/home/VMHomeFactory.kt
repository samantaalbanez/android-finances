package br.com.finances.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.finances.data.repository.SettingsRepository
import br.com.finances.data.repository.UserRepository

class VMHomeFactory(
    private val repositoryUser: UserRepository,
    private val repositorySettings: SettingsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMHome::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VMHome(repositoryUser, repositorySettings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
