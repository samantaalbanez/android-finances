package br.com.finances.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.com.finances.model.User
import br.com.finances.repository.UserRepository
import br.com.finances.repository.utils.StatusDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VMProfile(private val repository: UserRepository): ViewModel() {

    private var _status: StatusDataBase? = null
    val status: MutableLiveData<StatusDataBase> = MutableLiveData(StatusDataBase.IDLE)

    var user: LiveData<User> = repository.get.asLiveData()

    fun save(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                val response = async {
                    insertOrUpdate(
                        User(name = name)
                    )
                }
                response.await()
                status.value = _status
            }
        }
    }

    private fun insertOrUpdate(value: User) = CoroutineScope(Dispatchers.IO).launch {
        _status = if (user.value == null)
            repository.insert(value)
        else
            repository.update(value.name)
    }
}
