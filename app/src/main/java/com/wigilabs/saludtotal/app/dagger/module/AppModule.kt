package com.wigilabs.saludtotal.app.dagger.module

import android.content.Context
import com.wigilabs.saludtotal.app.SaludTotalApplication
import com.wigilabs.saludtotal.base.SaludTotalClient
import dagger.Module
import dagger.Provides

/***
 * Clase encargada de proveer informacion
 * El cual contiene informacion o datos puros que
 * se desee inyectar
 */
@Module
class AppModule(private val application: SaludTotalApplication) {


    @Provides
    fun provideApplication(): SaludTotalApplication = application

    @Provides
    fun provideApplicationContext(): Context = application.applicationContext

    @Provides
    fun provideApiClient(): SaludTotalClient = SaludTotalClient(application)


}
