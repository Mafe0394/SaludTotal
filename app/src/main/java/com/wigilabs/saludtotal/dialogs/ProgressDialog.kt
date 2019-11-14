package com.wigilabs.saludtotal.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.wigilabs.saludtotal.base.BaseDialogFragment
import com.wigilabs.saludtotal.databinding.DialogProgressBinding

class ProgressDialog : BaseDialogFragment() {

    var mensaje: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity ?: return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(activity)
        val binding = DialogProgressBinding.inflate(activity.layoutInflater)
        binding.mensaje = mensaje
        builder.setView(binding.root)
        return builder.create()
    }

}

fun FragmentActivity?.showProgressDialog(mensaje: String) {
    val dialog = ProgressDialog()
    dialog.mensaje = mensaje
    this?.let {
        dialog.show(it.supportFragmentManager, ProgressDialog::class.java.name)
    }
}

fun FragmentActivity?.closeProgressDialog() {
    val progressDialog = this?.supportFragmentManager?.findFragmentByTag(ProgressDialog::class.java.name)
    if (progressDialog is ProgressDialog) {
        try {
            progressDialog.dismissAllowingStateLoss()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}