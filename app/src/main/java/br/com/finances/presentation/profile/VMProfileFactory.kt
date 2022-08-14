package br.com.finances.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.finances.data.repository.UserRepository

class VMProfileFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMProfile::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VMProfile(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
