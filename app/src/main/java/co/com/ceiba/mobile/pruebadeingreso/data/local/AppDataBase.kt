package co.com.ceiba.mobile.pruebadeingreso.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.model.UserEntity

@Database(entities = [UserEntity::class, PostEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "user_table"
            ).build()
            return INSTANCE!!
        }
    }
}