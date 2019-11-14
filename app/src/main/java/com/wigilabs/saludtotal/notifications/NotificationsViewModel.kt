package com.wigilabs.saludtotal.notifications

import androidx.lifecycle.ViewModel
import com.wigilabs.saludtotal.adapters.NotificationsAdapter
import com.wigilabs.saludtotal.entities.NotificationModel

class NotificationsViewModel: ViewModel() {

    var listener: Listener? = null

    private var notificationsAdapter:NotificationsAdapter? = null

    fun setNotificationsInRecyclerAdapter(notifications: List<NotificationModel>){
        notificationsAdapter?.setNotificationsList(notifications)
        notificationsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerAdapter(): NotificationsAdapter{
        notificationsAdapter = NotificationsAdapter(this)
        return notificationsAdapter!!
    }

    fun getNotificationAt(position: Int): NotificationModel? {
        return notificationsAdapter?.notifications?.get(position)
    }

    interface Listener {
        fun loadNotifications()
    }

}