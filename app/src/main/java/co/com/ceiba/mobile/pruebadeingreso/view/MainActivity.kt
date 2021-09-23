package co.com.ceiba.mobile.pruebadeingreso.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import co.com.ceiba.mobile.pruebadeingreso.core.Resource
import co.com.ceiba.mobile.pruebadeingreso.data.local.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteUsersDataSource
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.presentation.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.UserViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.repository.RetrofitClient
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepositoryImpl
import co.com.ceiba.mobile.pruebadeingreso.view.adapter.OnClickListenerUser
import co.com.ceiba.mobile.pruebadeingreso.view.adapter.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.utils.CustomDialog

class MainActivity : AppCompatActivity(), OnClickListenerUser {
    private lateinit var bindig: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var dialogCus: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        userViewModel = ViewModelProvider(
            this, UserViewModelFactory(
                UserRepositoryImpl(
                    RemoteUsersDataSource(RetrofitClient.webService),
                    LocalUsersDataSource(AppDataBase.getDatabase(applicationContext).userDao())
                )
            )
        ).get()
        adapter = UserAdapter(mutableListOf(), this)
        dialogCus = CustomDialog(this)
        userFilter()

        userViewModel.fetchMainScreenUsers().observe(this, { result ->
            when(result) {
                is Resource.Loading -> {
                    dialogCus.showDialog()
                }
                is Resource.Success -> {
                    dialogCus.cancelDialog()
                    adapter = UserAdapter(result.data.result.toMutableList(), this)
                    bindig.recyclerViewSearchResults.adapter = adapter
                }
                is Resource.Failure -> {
                    dialogCus.cancelDialog()
                    Toast.makeText(this, "Error en consulta de usuarios: ${result.exception}", Toast.LENGTH_SHORT).show()
                    Log.d("Estado","${result.exception}")
                }
            }
        })
    }

    override fun onUserClick(user: User) {
        startActivity(Intent(this, PostActivity::class.java).
        putExtra("user", user))
    }

    private fun userFilter() {
        bindig.editTextSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val count = adapter.userFilter(p0.toString())
                if (count.isEmpty()) {
                    bindig.emptyView.root.visibility = View.VISIBLE
                } else {
                    bindig.emptyView.root.visibility = View.INVISIBLE
                }
            }
        })
    }
}