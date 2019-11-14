package com.wigilabs.saludtotal.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseDialogFragment
import com.wigilabs.saludtotal.databinding.DialogInfoBinding


data class InfoDialogModel(

    val subTitulo: String = "",

    val title: String = "",

    @StringRes val textoBtn: Int = R.string.cerrar,

    @ColorRes val bgBtn: Int = R.color.rojo,

    @ColorRes val colorTextoBtn: Int = R.color.colorWhite

)

class InfoDialog : BaseDialogFragment() {

    var model: InfoDialogModel = InfoDialogModel()

    var onClickAceptar: () -> Unit = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity ?: return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(activity)
        val binding = DialogInfoBinding.inflate(activity.layoutInflater)
        binding.clase = this
        binding.model = model
        builder.setView(binding.root)
        return builder.create()
    }

    fun aceptar() {
        onClickAceptar()
        dismiss()
    }

}

fun FragmentActivity?.showInfoDialog(model: InfoDialogModel): InfoDialog {
    val dialog = InfoDialog()
    dialog.model = model
    this?.let {
        dialog.show(it.supportFragmentManager, InfoDialog::class.java.name)
    }
    return dialog
}

fun FragmentActivity?.showInfoDialog(title: String,subTitle: String) =
    this.showInfoDialog(InfoDialogModel(title = title,subTitulo = subTitle))

fun FragmentActivity?.showInfoDialog(title: String) =
    this.showInfoDialog(InfoDialogModel(title = title))

fun Fragment.showInfoDialog(title: String) = activity.showInfoDialog(title)

fun Fragment.showInfoDialog(title: String,subTitle: String) = activity.showInfoDialog(title,subTitle)

fun Fragment.showInfoDialog(model: InfoDialogModel) = activity.showInfoDialog(model)