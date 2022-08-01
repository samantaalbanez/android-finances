package br.com.finances.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.com.finances.model.Selic
import br.com.finances.model.Settings
import br.com.finances.model.User
import br.com.finances.repository.SettingsRepository
import br.com.finances.repository.UserRepository
import br.com.finances.retrofit.selic.NetworkModule
import br.com.finances.retrofit.selic.SelicService
import br.com.finances.utils.CalculateUtils.calculateExpenses
import br.com.finances.utils.CalculateUtils.calculateInvestments
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VMHome(
    userRepository: UserRepository,
    settingsRepository: SettingsRepository
): ViewModel() {

    private val _selic = MutableLiveData<Selic>()
    val selic: MutableLiveData<Selic>
        get() = _selic

    private val _valueInvestments = MutableLiveData<String>()
    val valueInvestments: MutableLiveData<String>
        get() = _valueInvestments

    private val _valueExpenses = MutableLiveData<String>()
    val valueExpenses: MutableLiveData<String>
        get() = _valueExpenses

    var user: LiveData<User> = userRepository.get.asLiveData()
    var settings: LiveData<Settings> = settingsRepository.get.asLiveData()

    init {
        getRateSelic()
    }

    fun calculateValues() {
        calculateInvestments()
        calculateExpenses()
    }

    private fun calculateInvestments() {
        settings.value?.let { settings ->
            _valueInvestments.value = settings.salary.calculateInvestments()
        }
    }

    private fun calculateExpenses() {
        settings.value?.let { settings ->
            _valueExpenses.value = settings.salary.calculateExpenses()
        }
    }

    private fun getRateSelic() {
        viewModelScope.launch {
            val response = async {
                val service = NetworkModule.createNetworkService<SelicService>()
                val get = service.getSelicToday()[0]
                _selic.value = get
            }
            response.await()
        }
    }
}