package br.com.finances.database

import android.app.Application
import br.com.finances.repository.SettingsRepository
import br.com.finances.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ApplicationDataBase: Application() {

    private val database by lazy { DataBase.getDatabase(this) }

    val repositoryUser by lazy { UserRepository(database.userDAO()) }

    val repositorySettings by lazy { SettingsRepository(database.settingsDAO()) }
}