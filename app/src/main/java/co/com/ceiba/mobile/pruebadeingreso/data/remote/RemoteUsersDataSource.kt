package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.model.Post
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.repository.WebService

class RemoteUsersDataSource(private val webService: WebService) {
    suspend fun getUsers(): List<User> = webService.getUsers()

    suspend fun getPost(id: Int): List<Post> = webService.getPost(id)
}