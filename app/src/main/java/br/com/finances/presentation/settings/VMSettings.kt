package br.com.finances.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.com.finances.domain.model.Settings
import br.com.finances.data.repository.SettingsRepository
import br.com.finances.data.utils.StatusDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VMSettings(private val repository: SettingsRepository): ViewModel() {

    private var _status: StatusDataBase? = null
    val status: MutableLiveData<StatusDataBase> = MutableLiveData(StatusDataBase.IDLE)

    var settings: LiveData<Settings> = repository.get.asLiveData()

    fun save(salary: String) {
        if (salary.isNotEmpty()) {
            viewModelScope.launch {
                val response = async {
                    insertOrUpdate(
                        Settings(salary = salary.toDouble())
                    )
                }
                response.await()
                status.value = _status
            }
        }
    }

    private fun insertOrUpdate(value: Settings) = CoroutineScope(Dispatchers.IO).launch {
         _status = if (settings.value == null)
            repository.insert(value)
        else
            repository.update(value.salary)
    }
}
