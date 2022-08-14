package br.com.finances.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.finances.data.dao.SettingsDAO
import br.com.finances.data.dao.UserDAO
import br.com.finances.domain.model.Settings
import br.com.finances.domain.model.User

@Database(entities = [User::class, Settings::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun settingsDAO(): SettingsDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(
            context: Context
        ): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "finances-db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}