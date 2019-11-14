package com.wigilabs.saludtotal.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityStartBinding
import com.wigilabs.saludtotal.entities.LoginModel
import com.wigilabs.saludtotal.login.LoginActivity
import com.wigilabs.saludtotal.register.RegisterActivity
import io.reactivex.disposables.CompositeDisposable
import saveEncryptedData

class StartActivity : BaseActivity() {

    lateinit var binding:ActivityStartBinding

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_start)

        startBottonNavigationMenu()

        getTokenPublic()

    }

    private fun getTokenPublic(){
        disposable.add(saludTotalClient.
            login(LoginModel("AFI","N","800149453",
                "C","999","saludtotal","APP"))
            .subscribe {
            if(it.get("Token")!=null) {
                var token = it.get("Token").toString()
                token = token.replace("\"","")
                saveEncryptedData("tkp", token)
            }
        })
    }


    fun onClickRegistrate(view:View){
        startActivity(Intent(view.context,RegisterActivity::class.java))
    }

    fun onClickIniciaSesion(view:View){
        startActivity(Intent(view.context,LoginActivity::class.java))
    }

}






