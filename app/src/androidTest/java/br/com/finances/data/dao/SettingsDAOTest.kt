package br.com.finances.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.finances.data.database.DataBase
import br.com.finances.domain.model.Settings
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsDAOTest {

    private lateinit var settingsDAO: SettingsDAO
    private lateinit var db: DataBase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, DataBase::class.java)
            .allowMainThreadQueries()
            .build()
        settingsDAO = db.settingsDAO()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun when_insert_setting_in_database_then_return_the_value_in_select() = runBlocking {
        val setting = Settings(salary = 1000.0)
        settingsDAO.insert(setting)
        val get: Settings = settingsDAO.get().first()
        assertEquals(get.salary, setting.salary)
    }

    @Test
    @Throws(Exception::class)
    fun when_update_setting_in_database_then_return_the_value_in_select() = runBlocking {
        val setting = Settings(salary = 1000.0)
        settingsDAO.insert(setting)
        settingsDAO.update(2000.0)
        val get: Settings = settingsDAO.get().first()
        assertEquals(get.salary, 2000.0)
    }

}