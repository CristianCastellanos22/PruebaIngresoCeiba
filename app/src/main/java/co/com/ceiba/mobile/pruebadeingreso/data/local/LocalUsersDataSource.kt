package co.com.ceiba.mobile.pruebadeingreso.data.local

import co.com.ceiba.mobile.pruebadeingreso.data.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.data.model.UsersList
import co.com.ceiba.mobile.pruebadeingreso.data.model.toUserList

class LocalUsersDataSource(private val userDao: UserDao) {

    suspend fun getUsers(): UsersList {
        return userDao.getAllUsers().toUserList()
    }

    suspend fun saveUsers(user: UserEntity) {
        userDao.saveUsers(user)
    }
}
