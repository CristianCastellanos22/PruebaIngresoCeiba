package co.com.ceiba.mobile.pruebadeingreso.view.utils

import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UsersModule::class])
interface UsersComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(postActivity: PostActivity)
}