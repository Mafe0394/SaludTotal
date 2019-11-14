package com.wigilabs.saludtotal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wigilabs.saludtotal.BR
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.databinding.ItemAffiliationStatusBinding
import com.wigilabs.saludtotal.databinding.ItemNotificationBinding
import com.wigilabs.saludtotal.entities.NotificationModel


class AffiliationStatusAdapter :
        RecyclerView.Adapter<AffiliationStatusAdapter.HolderNotificationAdapter>() {

    var statusList: List<NotificationModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): HolderNotificationAdapter {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_affiliation_status,
            viewGroup, false)
        return HolderNotificationAdapter(view)
    }

    override fun getItemCount(): Int = statusList.size

    override fun onBindViewHolder(holder: HolderNotificationAdapter, position: Int) {

        holder.binding.tvAffiliatedName.text = statusList[position].hour
        holder.binding.tvDocumentId.text = statusList[position].type
        holder.binding.tvAffiliationType.text = statusList[position].date
        holder.binding.tvStatus.text = statusList[position].place

    }

    fun setAffiliationStatusList(statusList: List<NotificationModel>){
        this.statusList = statusList
    }

    class HolderNotificationAdapter(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemAffiliationStatusBinding = ItemAffiliationStatusBinding.bind(view)
    }

}