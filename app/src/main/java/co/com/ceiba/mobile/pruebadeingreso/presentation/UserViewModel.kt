package co.com.ceiba.mobile.pruebadeingreso.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.com.ceiba.mobile.pruebadeingreso.core.Resource
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository
import kotlinx.coroutines.Dispatchers


class UserViewModel(private val repo: UserRepository) : ViewModel() {
    fun fetcMainScreenUsers() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getUsers()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}