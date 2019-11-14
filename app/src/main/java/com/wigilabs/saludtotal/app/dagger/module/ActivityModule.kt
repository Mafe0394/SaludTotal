package com.wigilabs.saludtotal.app.dagger.module


import com.wigilabs.saludtotal.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: BaseActivity) {

    @Provides
    fun provideActivity(): BaseActivity = activity

}