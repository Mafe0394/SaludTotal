package com.wigilabs.saludtotal.apps

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.utils.openAppInGooglePlay

class AppsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apps)
        findViewById<ImageView>(R.id.btn_open_app).setOnClickListener {
            openAppInGooglePlay(this,"com.saludtotal.saludtotaleps")
        }
        startActionBar("APLICACIONES RELACIONADAS")
    }


}
