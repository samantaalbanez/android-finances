package br.com.finances.data.repository

import androidx.annotation.WorkerThread
import br.com.finances.data.dao.SettingsDAO
import br.com.finances.domain.model.Settings
import br.com.finances.data.utils.StatusDataBase
import br.com.finances.data.utils.toStatusDB
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val settingsDAO: SettingsDAO) {

    val get: Flow<Settings> = settingsDAO.get()

    @WorkerThread
    fun insert(settings: Settings): StatusDataBase {
        return settingsDAO.insert(settings).toStatusDB()
    }

    @WorkerThread
    fun update(salary: Double): StatusDataBase {
        return settingsDAO.update(salary).toStatusDB()
    }
}