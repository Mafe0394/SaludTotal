package com.wigilabs.saludtotal.register

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity(){


    lateinit var binding:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_register)

        startActionBar("REGÍSTRATE")
    }




}
