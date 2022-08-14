package br.com.finances.data.repository

import androidx.annotation.WorkerThread
import br.com.finances.data.dao.UserDAO
import br.com.finances.domain.model.User
import br.com.finances.data.utils.StatusDataBase
import br.com.finances.data.utils.toStatusDB
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDAO: UserDAO) {

   val get: Flow<User> = userDAO.get()

    @WorkerThread
    fun insert(user: User): StatusDataBase {
        return userDAO.insert(user).toStatusDB()
    }

    @WorkerThread
    fun update(name: String): StatusDataBase {
        return userDAO.update(name).toStatusDB()
    }
}