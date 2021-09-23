package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(user: UserEntity)

    @Query("SELECT * FROM PostEntity WHERE PostEntity.userId = :id")
    suspend fun getPostLocal(id: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePost(postEntity: PostEntity)
}