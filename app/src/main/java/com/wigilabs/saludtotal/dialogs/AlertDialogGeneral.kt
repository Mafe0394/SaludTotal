package com.wigilabs.saludtotal.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseDialogFragment
import com.wigilabs.saludtotal.databinding.DialogAlertBinding


data class AlertDialogModel(

    val subTitulo: String = "",

    val mensaje: String = "",

    val textoBtnAceptar: Int = R.string.ok,

    val textoBtnCancelar: Int = R.string.cancelar,

    val icono: Int = 0

)

class AlertDialogGeneral : BaseDialogFragment() {

    private lateinit var activity: AppCompatActivity

    private lateinit var binding: DialogAlertBinding

    private lateinit var model: AlertDialogModel

    var onClickAceptar: () -> Unit = {}

    var onClickCancelar: () -> Unit = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        binding = DialogAlertBinding.inflate(activity.layoutInflater)
        binding.model = model
        binding.clase = this
        builder.setView(binding.root)
        return builder.create()
    }

    fun show(activity: AppCompatActivity, model: AlertDialogModel) {
        this.activity = activity
        this.model = model
        show(activity.supportFragmentManager, AlertDialog::class.java.name)
    }

    fun aceptar() {
        dismiss()
        onClickAceptar()
    }

    fun cancelar() {
        dismiss()
        onClickCancelar()
    }

}
