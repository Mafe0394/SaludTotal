package com.wigilabs.saludtotal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wigilabs.saludtotal.BR
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.databinding.ItemNotificationBinding
import com.wigilabs.saludtotal.entities.NotificationModel
import com.wigilabs.saludtotal.notifications.NotificationsViewModel


class NotificationsAdapter(private var notificationsViewModel: NotificationsViewModel) :
        RecyclerView.Adapter<NotificationsAdapter.HolderNotificationAdapter>() {

    var notifications: List<NotificationModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): HolderNotificationAdapter {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_notification,
            viewGroup, false)
        return HolderNotificationAdapter(view)
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: HolderNotificationAdapter, position: Int) {

        holder.binding.containerView.setOnClickListener { onClick(notifications[position], position) }

        holder.binding.tvHour.text = notifications[position].hour
        holder.binding.tvType.text = notifications[position].type
        holder.binding.tvDate.text = notifications[position].date
        holder.binding.tvPlace.text = notifications[position].place

    }

    private fun onClick(item: NotificationModel, posicion: Int) {

    }

    fun setNotificationsList(notifications: List<NotificationModel>){
        this.notifications = notifications
    }

    class HolderNotificationAdapter(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemNotificationBinding = ItemNotificationBinding.bind(view)
        fun setDataItem(notificationsViewModel: NotificationsViewModel, posicion: Int){
            binding.setVariable(BR.viewmodel,notificationsViewModel)
            binding.setVariable(BR.position,posicion)
            binding.executePendingBindings()
        }
    }

}