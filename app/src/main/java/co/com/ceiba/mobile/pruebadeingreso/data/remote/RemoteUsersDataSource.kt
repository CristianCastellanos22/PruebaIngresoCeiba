package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.model.UsersList
import co.com.ceiba.mobile.pruebadeingreso.repository.WebService

class RemoteUsersDataSource(private val webService: WebService) {
    suspend fun getUsers(): UsersList = webService.getUsers()
}