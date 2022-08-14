package br.com.finances.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.finances.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM user LIMIT 1")
    fun get(): Flow<User>

    @Insert
    fun insert(users: User): Long

    @Query("UPDATE user SET name = :name")
    fun update(name: String): Int
}