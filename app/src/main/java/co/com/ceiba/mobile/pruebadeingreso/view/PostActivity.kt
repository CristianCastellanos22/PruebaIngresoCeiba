package co.com.ceiba.mobile.pruebadeingreso.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import co.com.ceiba.mobile.pruebadeingreso.core.Resource
import co.com.ceiba.mobile.pruebadeingreso.data.local.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.presentation.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.UserViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.repository.RetrofitClient
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepositoryImpl
import co.com.ceiba.mobile.pruebadeingreso.view.adapter.PostAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.utils.CustomDialog

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var postViewModel: UserViewModel
    private lateinit var userItem: User
    private lateinit var dialogCus: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postViewModel = ViewModelProvider(
            this, UserViewModelFactory(
                UserRepositoryImpl(
                    RemoteUsersDataSource(RetrofitClient.webService),
                    LocalUsersDataSource(AppDataBase.getDatabase(applicationContext).userDao())
                )
            )
        ).get()

        dialogCus = CustomDialog(this)
        getData()

        userItem.id.let {
            postViewModel.getPost(it).observe(this, { result ->
                when(result) {
                    is Resource.Loading -> {
                        dialogCus.showDialog()
                    }
                    is Resource.Success -> {
                        dialogCus.cancelDialog()
                        binding.recyclerViewPostsResults.adapter = PostAdapter(result.data.resultPost)
                    }
                    is Resource.Failure -> {
                        dialogCus.cancelDialog()
                        Toast.makeText(this, "Error en consulta de post: ${result.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun getData() {
        intent.getParcelableExtra<User>("user")?.let {
            userItem = it
            binding.name.text = it.name
            binding.phone.text = it.phone
            binding.email.text = it.email
        }
    }
}