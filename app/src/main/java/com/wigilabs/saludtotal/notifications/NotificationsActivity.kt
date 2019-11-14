package com.wigilabs.saludtotal.notifications

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityNotificationsBinding
import com.wigilabs.saludtotal.entities.NotificationModel

class NotificationsActivity : BaseActivity(), NotificationsViewModel.Listener {


    lateinit var binding:ActivityNotificationsBinding

    private val viewModel: NotificationsViewModel
        get() = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)

    var notifications: ArrayList<NotificationModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications)
        binding.viewmodel = viewModel
        viewModel.listener = this
        startActionBar("NOTIFICACIONES")

        binding.rvNotifications.layoutManager = LinearLayoutManager(this)
        binding.rvNotifications.adapter = viewModel.getRecyclerAdapter()
        loadNotifications()

    }


    override fun loadNotifications() {
        notifications.add(NotificationModel("5pm","Odontologo",
            "Martes, 10 de diciembre","Hospital C"))
        notifications.add(NotificationModel("4am","Odontologo",
            "Jueves, 16 de diciembre","Clinica A"))
        if(notifications.size > 0){
            binding.tvNotData.visibility = View.GONE
        }
        viewModel.setNotificationsInRecyclerAdapter(notifications)
    }

}
