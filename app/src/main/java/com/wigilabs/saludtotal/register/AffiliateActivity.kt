package com.wigilabs.saludtotal.register

import android.graphics.PorterDuff
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityAffiliateBinding
import com.wigilabs.saludtotal.dialogs.showInfoDialog
import io.reactivex.disposables.CompositeDisposable
import loadEncryptedData
import androidx.core.content.ContextCompat
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.entities.CiudadesPacModel
import delete
import fromJson
import load
import save
import saveEncryptedData
import toJson


class AffiliateActivity : BaseActivity(), AffiliateViewModel.Listener {

    private val viewModel: AffiliateViewModel
        get() = ViewModelProviders.of(this).get(AffiliateViewModel::class.java)

    lateinit var binding:ActivityAffiliateBinding
    private var ciudadesPacModel: CiudadesPacModel = CiudadesPacModel()
    private val disposable = CompositeDisposable()
    private var typeDocumentId = 0
    private var cityId = 0
    private var hourId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_affiliate)
        binding.viewmodel = viewModel
        viewModel.listener = this
        initViews()
        getTokenSecret(loadEncryptedData("tkp"))
        Toast.makeText(this,"Token: "+loadEncryptedData("tkp"),
            Toast.LENGTH_SHORT).show()
    }

    private fun initViews(){

        startActionBar("AFÍLIATE")
        setSpinnerLista(resources.getStringArray(R.array.tipo_documento),R.id.sp_tipoId_afiliate)
        setSpinnerLista(resources.getStringArray(R.array.tipo_documento),R.id.sp_Ciudad)

        binding.lbPrivacyPolicies.movementMethod = LinkMovementMethod.getInstance()
        binding.cbAcceptTerms.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                binding.btnAffiliateEnviar.background.setColorFilter(ContextCompat.getColor(this,
                    R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP)
            }else{
                binding.btnAffiliateEnviar.background.setColorFilter(ContextCompat.getColor(this,
                    R.color.colorGray), PorterDuff.Mode.SRC_ATOP)
            }
        }
        binding.spTipoIdAfiliate.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?, position: Int, id: Long) {
                    typeDocumentId = position
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        binding.spCiudad.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?, position: Int, id: Long) {
                    cityId = position
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    override fun loadSpinners() {

    }

    override fun verifyForm() {

        val documentId = binding.etNumID.text.toString()
        val phoneF = binding.etTelFijo.text.toString()
        val phone = binding.etTelCelular.text.toString()
        val email = binding.etEmail.text.toString()
        val emailC = binding.etEmailConf.text.toString()

        if(binding.etNombres.text.isEmpty()){
            viewModel.listener!!.onFormError("Debe ingresar su nombre")
            return
        }
        if(binding.etApellidos.text.isEmpty()){
            viewModel.listener!!.onFormError("Debe ingresar sus apellidos")
            return
        }
        if(typeDocumentId==0) {
            viewModel.listener!!.onFormError(getString(R.string.et_tipo_identificacion))
            return
        }
        if(documentId.isEmpty()) {
            viewModel.listener!!.onFormError("Debe ingresar número de identificación")
            return
        }else {
            if(documentId.length <= 5){
                viewModel.listener!!.onFormError("Número de identificación invalido")
                return
            }
        }
        if(phoneF.isEmpty()){
            viewModel.listener!!.onFormError("Debe ingresar el número de teléfono fijo")
            return
        }else{
            if(phoneF.length <= 3){
                viewModel.listener!!.onFormError("Número de teléfono invalido")
                return
            }
        }
        if(phone.isEmpty()){
            viewModel.listener!!.onFormError("Debe ingresar el número de celular")
            return
        }else{
            if(phone.length <= 5){
                viewModel.listener!!.onFormError("Número de celular invalido")
                return
            }
        }
        if(email.isEmpty()){
            viewModel.listener!!.onFormError("Debe ingresar su correo electrónico")
            return
        }else{
            if(!email.contains("@")){
                viewModel.listener!!.onFormError("Correo electrónico invalido")
                return
            }
        }
        if(emailC.isEmpty()){
            viewModel.listener!!.onFormError("Debe confirmar su correo electrónico")
            return
        }
        if(email != emailC){
            viewModel.listener!!.onFormError("Correo electrónico no coinciden")
            return
        }
        if(cityId==0) {
            viewModel.listener!!.onFormError("Debe seleccionar una ciudad")
            return
        }
        if(hourId==0) {
            viewModel.listener!!.onFormError("Debe seleccionar una hora")
            return
        }
        if(binding.etComentarios.text.isEmpty()){
            viewModel.listener!!.onFormError("Debe ingresar comentarios")
            return
        }
        if(!binding.cbAcceptTerms.isChecked){
            viewModel.listener!!.onFormError("Debe autorizar el tratamiento de datos")
            return
        }
        registerUser()

    }

    override fun onFormError(error: String) {
        showInfoDialog(error)
    }

    override fun registerUser() {

    }

    private fun getTokenSecret(token: String){
        disposable.add(saludTotalClient.
            getTokenComoAfiliarme(token,"APP")
            .subscribe {
                if(it.get("Token")!=null && it.get("Token").toString().isNotEmpty()) {
                    var tokenSecret = it.get("Token").toString()
                    tokenSecret = tokenSecret.replace("\"","")
                    saveEncryptedData("tkpS",tokenSecret)
                    if(load("ciudadesPac").isEmpty()){
                        getCitiesPac()
                    }else{
                        hideContentLoadingProgressBar()
                    }
                }else{
                    hideContentLoadingProgressBar()
                }
            })
    }


    private fun getCitiesPac(){
        disposable.add(saludTotalClient.
            getCitiesPac()
            .subscribe {
                if(checkError(it)) {
                    ciudadesPacModel = fromJson(it, CiudadesPacModel::class.java)
                    save("ciudadesPac", toJson(it))
                }
                hideContentLoadingProgressBar()
            })
    }


    private fun sendDatas(){
        
        showContentLoadingProgressBar()
        disposable.add(saludTotalClient.
            registrarSolicitud("","APP")
            .subscribe {
                if(checkError(it)) {
                   delete("tkpS")
                }
                hideContentLoadingProgressBar()
            })

    }


    override fun showContentLoadingProgressBar() {
        binding.contentLoadingProgressBar.visibility = View.VISIBLE
        binding.contentLoadingProgressBar.show()
    }

    override fun hideContentLoadingProgressBar() {
        binding.contentLoadingProgressBar.hide()
        binding.contentLoadingProgressBar.visibility = View.GONE
    }

}
