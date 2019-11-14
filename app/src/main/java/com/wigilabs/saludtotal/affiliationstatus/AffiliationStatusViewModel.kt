package com.wigilabs.saludtotal.affiliationstatus

import androidx.lifecycle.ViewModel
import com.wigilabs.saludtotal.adapters.AffiliationStatusAdapter
import com.wigilabs.saludtotal.entities.NotificationModel

class AffiliationStatusViewModel : ViewModel() {

    var listener: Listener? = null

    private var affiliationStatusAdapter:AffiliationStatusAdapter? = null

    fun setStatusInRecyclerAdapter(notifications: List<NotificationModel>){
        affiliationStatusAdapter?.setAffiliationStatusList(notifications)
        affiliationStatusAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerAdapter(): AffiliationStatusAdapter{
        affiliationStatusAdapter = AffiliationStatusAdapter()
        return affiliationStatusAdapter!!
    }

    interface Listener {
        fun loadAffiliationStatus()
    }

}
