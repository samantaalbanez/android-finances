package br.com.finances.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.finances.repository.SettingsRepository
import br.com.finances.repository.UserRepository
import br.com.finances.viewmodel.VMHome

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
