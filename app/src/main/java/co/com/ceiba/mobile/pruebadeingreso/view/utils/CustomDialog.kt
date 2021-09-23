package co.com.ceiba.mobile.pruebadeingreso.view.utils

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import co.com.ceiba.mobile.pruebadeingreso.databinding.DialogViewBinding

class CustomDialog(private val activity: Activity) {
    private lateinit var dialog: Dialog
    private lateinit var binding: DialogViewBinding

    fun showDialog() {
        binding = DialogViewBinding.inflate(LayoutInflater.from(activity.applicationContext))
        dialog = Dialog(activity)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun cancelDialog() {
        dialog.dismiss()
    }
}