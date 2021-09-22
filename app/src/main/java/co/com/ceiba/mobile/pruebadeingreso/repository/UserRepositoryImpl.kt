package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.core.InternetCheck
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.model.UsersList
import co.com.ceiba.mobile.pruebadeingreso.data.model.toUserEntity
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteUsersDataSource

class UserRepositoryImpl(private val dataSourceRemote: RemoteUsersDataSource, private val dataSourceLocal: LocalUsersDataSource): UserRepository {
    override suspend fun getUsers(): UsersList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUsers().result.forEach { user ->
                dataSourceLocal.saveUsers(user.toUserEntity())
            }
            return dataSourceLocal.getUsers()
        } else {
            dataSourceLocal.getUsers()
        }
    }

}