package br.com.finances.data.database

import android.app.Application
import br.com.finances.data.repository.SettingsRepository
import br.com.finances.data.repository.UserRepository

class ApplicationDataBase: Application() {

    private val database by lazy { DataBase.getDatabase(this) }

    val repositoryUser by lazy { UserRepository(database.userDAO()) }

    val repositorySettings by lazy { SettingsRepository(database.settingsDAO()) }
}