package co.com.ceiba.mobile.pruebadeingreso.view.utils

import android.content.Context
import co.com.ceiba.mobile.pruebadeingreso.data.local.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.presentation.UserViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.repository.RetrofitClient
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsersModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideUsersViewModelFactory() = UserViewModelFactory(provideRepositoryUserImpl())

    @Provides
    @Singleton
    fun provideRepositoryUserImpl() = UserRepositoryImpl(
        RemoteUsersDataSource(RetrofitClient.webService),
        LocalUsersDataSource(AppDataBase.getDatabase(context).userDao())
    )
}