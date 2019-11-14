package com.wigilabs.saludtotal.register

import androidx.lifecycle.ViewModel
import com.wigilabs.saludtotal.adapters.NotificationsAdapter
import com.wigilabs.saludtotal.entities.NotificationModel

class AffiliateViewModel: ViewModel() {

    var listener: Listener? = null

    fun verifyForm(){
        listener!!.verifyForm()
    }

    interface Listener {
        fun loadSpinners()
        fun verifyForm()
        fun showContentLoadingProgressBar()
        fun hideContentLoadingProgressBar()
        fun onFormError(error: String)
        fun registerUser()
    }

}