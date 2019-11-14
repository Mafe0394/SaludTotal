package com.wigilabs.saludtotal.app.dagger


import com.wigilabs.saludtotal.app.dagger.module.ActivityModule
import com.wigilabs.saludtotal.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(dependencies = [(AppComponent::class)],modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)
    fun activity(): BaseActivity
}