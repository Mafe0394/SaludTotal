package com.wigilabs.saludtotal.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.JsonObject
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.app.SaludTotalApplication
import com.wigilabs.saludtotal.app.dagger.ActivityComponent
import com.wigilabs.saludtotal.app.dagger.DaggerActivityComponent
import com.wigilabs.saludtotal.app.dagger.module.ActivityModule
import com.wigilabs.saludtotal.apps.AppsActivity
import com.wigilabs.saludtotal.dialogs.showProgressDialog
import com.wigilabs.saludtotal.dialogs.closeProgressDialog
import com.wigilabs.saludtotal.dialogs.showInfoDialog
import com.wigilabs.saludtotal.news.NewsActivity
import com.wigilabs.saludtotal.register.AffiliateActivity
import com.wigilabs.saludtotal.start.StartActivity
import getError
import haveError
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }


    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as SaludTotalApplication).appComponent)
            .build()
    }


    @Inject
    lateinit var saludTotalClient: SaludTotalClient


    fun delaySplashScreen(){
        val delaySplash:Long = 3000
        Handler().postDelayed({
            //Start startActivity
            startActivity(Intent(this,StartActivity::class.java))
            finish()
        },delaySplash)
    }

    fun startBottonNavigationMenu(){
        val bottomNavigationView: BottomNavigationView =findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
                menuItem ->
            when (menuItem.itemId){
                R.id.afiliate ->{
                    Toast.makeText(this,"Afiliate", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,AffiliateActivity::class.java))
                    true
                }
                R.id.directorio ->{
                    Toast.makeText(this,"Directorio", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.noticias ->{
                    Toast.makeText(this,R.string.cargando, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,NewsActivity::class.java))
                    true
                }
                R.id.apps ->{
                    startActivity(Intent(this,AppsActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }


    fun startActionBar(title:String){
        val titleAB= findViewById<TextView>(R.id.titleAB)
        titleAB.text = title
        setSupportActionBar(findViewById(R.id.ActionBar_r))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_st_general_back)
    }


    fun setSpinnerLista(Lista:Array<String>, spinnerId:Int){
        val spinner1 = findViewById<Spinner>(spinnerId)
        val adapter = ArrayAdapter<String>(this,
            R.layout.spinner_text_style,
            Lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner1.adapter = adapter
    }


    fun checkError(jsonObject: JsonObject, callback: () -> Unit = {}): Boolean {
        if (haveError(jsonObject)) {
            val error = getError(jsonObject)
            showInfoDialog(error).onClickAceptar = callback
            return false
        }
        return true
    }


    fun showProgressDialog() {
        showProgressDialog(getString(R.string.cargando))
    }


    fun dismissProgressDialog() {
        if (!isFinishing) {
            closeProgressDialog()
        }
    }


}