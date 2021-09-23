package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.data.model.PostsList
import co.com.ceiba.mobile.pruebadeingreso.data.model.UsersList

interface UserRepository {
    suspend fun getUsers(): UsersList
    suspend fun getPost(id: Int): PostsList
}