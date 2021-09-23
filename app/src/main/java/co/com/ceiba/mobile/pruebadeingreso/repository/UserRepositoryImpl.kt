package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.core.InternetCheck
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.model.*
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteUsersDataSource

class UserRepositoryImpl(private val dataSourceRemote: RemoteUsersDataSource, private val dataSourceLocal: LocalUsersDataSource): UserRepository {
    override suspend fun getUsers(): UsersList {
        return if (InternetCheck.isNetworkAvailable()) {
            if (dataSourceLocal.getUsers().result.isNotEmpty()) {
                dataSourceLocal.getUsers()
            } else {
                dataSourceRemote.getUsers().forEach { user ->
                    dataSourceLocal.saveUsers(user.toUserEntity())
                }
            }
            return dataSourceLocal.getUsers()
        } else {
            dataSourceLocal.getUsers()
        }
    }

    override suspend fun getPost(id: Int): PostsList {
        return if (InternetCheck.isNetworkAvailable()) {
            if (dataSourceLocal.getPost(id).resultPost.isNotEmpty()) {
                dataSourceLocal.getPost(id)
            } else {
                dataSourceRemote.getPost(id).forEach { post ->
                    dataSourceLocal.savePost(post.toPostEntity())
                }
                return dataSourceLocal.getPost(id)
            }
        } else {
            dataSourceLocal.getPost(id)
        }
    }

}