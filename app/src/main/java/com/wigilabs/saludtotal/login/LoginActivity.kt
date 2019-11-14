package com.wigilabs.saludtotal.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityLoginBinding
import com.wigilabs.saludtotal.dialogs.showInfoDialog
import com.wigilabs.saludtotal.entities.LoginModel
import fromJson
import haveError
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import loadEncryptedData
import saveEncryptedData


class LoginActivity : BaseActivity(), LoginViewModel.Listener {


    lateinit var binding:ActivityLoginBinding

    private val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this).get(LoginViewModel::class.java)

    private var listTypeDocumentId:Array<String>? = null
    private var typeDocumentId = 0
    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel = viewModel
        viewModel.listener = this
        startActionBar("INICIA SESIÓN")
        listTypeDocumentId = resources.getStringArray(R.array.tipo_documento_id)
        loadTipoIdentificacion()
    }


    override fun onLogin() {

        val documentId = binding.etLoginIdentificacion.text.toString()

        if(typeDocumentId==0) {
            viewModel.listener!!.onError(getString(R.string.et_tipo_identificacion))
            return
        }
        if(documentId.isEmpty()) {
            viewModel.listener!!.onError("Debe ingresar número de identificación")
            return
        }else {
            if(documentId.length <= 5){
                viewModel.listener!!.onError("Número de identificación invalido")
                return
            }
        }
        if(binding.etLoginContrasenha.text.isEmpty()) {
            viewModel.listener?.onError("Debe ingresar contraseña")
            return
        }
        login(LoginModel("AFI","N","800149453",
            listTypeDocumentId!![typeDocumentId-1],
            binding.etLoginIdentificacion.text.toString(),
            binding.etLoginContrasenha.text.toString()))
    }


    private fun login(login: LoginModel): Disposable {
        showProgressDialog()
        disposable.add(
            saludTotalClient.login(login).subscribe {
                if(checkError(it)) {
                    val isValido = it.get("Valido").asBoolean
                    if(isValido){
                        val token = it.get("Token")
                        saveEncryptedData("Done213",token.toString())
                        //getToken(loadEncryptedData("Done213"))
                    }
                }else{
                    dismissProgressDialog()
                }
            }
        )
        return disposable
    }



    private fun getProfile(){

    }


    override fun loadTipoIdentificacion() {
        setSpinnerLista(resources.getStringArray(R.array.tipo_documento),
            R.id.sp_login_tipo_documento)
        binding.spLoginTipoDocumento.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?, position: Int, id: Long) {
                    typeDocumentId = position
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    override fun onError(error: String) {
        showInfoDialog(error)
    }

}
