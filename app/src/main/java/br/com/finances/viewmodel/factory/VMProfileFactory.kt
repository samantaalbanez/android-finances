package br.com.finances.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.finances.repository.UserRepository
import br.com.finances.viewmodel.VMProfile

class VMProfileFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMProfile::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VMProfile(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
