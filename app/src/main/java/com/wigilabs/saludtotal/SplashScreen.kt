package com.wigilabs.saludtotal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.utils.registerNetworkCallback
import delete


class SplashScreen : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        registerNetworkCallback(this)
        delete("tkp")
        delete("tkpS")
        delete("ciudadesPac")
        delaySplashScreen()
    }
}
