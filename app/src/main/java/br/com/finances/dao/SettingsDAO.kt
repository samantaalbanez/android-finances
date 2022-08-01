package br.com.finances.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.finances.model.Settings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDAO {

    @Query("SELECT * FROM settings LIMIT 1")
    fun get(): Flow<Settings>

    @Insert
    fun insert(settings: Settings): Long

    @Query("UPDATE settings SET salary = :salary")
    fun update(salary: Double): Int
}