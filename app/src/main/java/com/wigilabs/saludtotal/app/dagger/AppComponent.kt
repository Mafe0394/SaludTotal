package com.wigilabs.saludtotal.app.dagger

import android.content.Context
import com.wigilabs.saludtotal.app.SaludTotalApplication
import com.wigilabs.saludtotal.app.dagger.module.AppModule
import com.wigilabs.saludtotal.base.SaludTotalClient
import dagger.Component
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {


    fun inject(saludTotalApplication: SaludTotalApplication)


    fun application(): SaludTotalApplication


    fun applicationContext(): Context


    fun network(): SaludTotalClient

}