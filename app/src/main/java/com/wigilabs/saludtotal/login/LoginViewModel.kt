package com.wigilabs.saludtotal.login

import android.content.Context
import android.widget.ArrayAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.entities.LoginModel

class LoginViewModel: ViewModel() {

    var listener: Listener? = null

    fun onClickLogin() {
        listener?.onLogin()
    }

    interface Listener {
        fun onLogin()
        fun onError(error: String)
        fun loadTipoIdentificacion()
    }

}