package com.wigilabs.saludtotal.app

import android.app.Application
import com.wigilabs.saludtotal.app.dagger.AppComponent
import com.wigilabs.saludtotal.app.dagger.DaggerAppComponent
import com.wigilabs.saludtotal.app.dagger.module.AppModule
import javax.inject.Inject


class SaludTotalApplication : Application()  {


    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        this.appComponent.inject(this)
    }


}