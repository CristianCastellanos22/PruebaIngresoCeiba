package co.com.ceiba.mobile.pruebadeingreso.data.local

import co.com.ceiba.mobile.pruebadeingreso.data.model.*
import co.com.ceiba.mobile.pruebadeingreso.data.model.UsersList

class LocalUsersDataSource(private val userDao: UserDao) {

    suspend fun getUsers(): UsersList {
        return userDao.getAllUsers().toUserList()
    }

    suspend fun saveUsers(user: UserEntity) {
        userDao.saveUsers(user)
    }

    suspend fun getPost(id: Int): PostsList {
        return userDao.getPostLocal(id).toPostList()
    }

    suspend fun savePost(post: PostEntity) {
        userDao.savePost(post)
    }
}
