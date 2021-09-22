package co.com.ceiba.mobile.pruebadeingreso.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindig: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)
    }

    override fun onStart() {
        super.onStart()
    }
}