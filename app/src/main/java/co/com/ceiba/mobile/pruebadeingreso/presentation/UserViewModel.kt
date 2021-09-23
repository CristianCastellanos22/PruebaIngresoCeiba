package co.com.ceiba.mobile.pruebadeingreso.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import co.com.ceiba.mobile.pruebadeingreso.core.Resource
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository
import kotlinx.coroutines.Dispatchers


class UserViewModel(private val repo: UserRepository) : ViewModel() {
    fun fetchMainScreenUsers() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getUsers()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

    fun getPost(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPost(id)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class UserViewModelFactory(private val repo: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java).newInstance(repo)
    }

}