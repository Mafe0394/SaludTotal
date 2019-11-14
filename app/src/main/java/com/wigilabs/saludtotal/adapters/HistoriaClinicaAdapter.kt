package com.wigilabs.saludtotal.adapters

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.databinding.RvHistoriaClinicaLayoutBinding
import com.wigilabs.saludtotal.entities.CitaMedicaModel
import com.wigilabs.saludtotal.home.HomeActivity


import java.util.ArrayList

class ContactoAdaptador(
    internal var citaMedicaModels: ArrayList<CitaMedicaModel>,
    internal var activity: Activity
) : RecyclerView.Adapter<ContactoAdaptador.CitaMedicaModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaMedicaModelViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.rv_historia_clinica_layout,
            parent, false)
        return CitaMedicaModelViewHolder(binding)
    }

    override fun onBindViewHolder(citaMedicaModelViewHolder: CitaMedicaModelViewHolder, position: Int) {
        val citaMedicaModel = citaMedicaModels[position]
        citaMedicaModelViewHolder.bindConnection(citaMedicaModel)
        citaMedicaModelViewHolder.binding.lyHistoriaClinicaSelect.setOnClickListener(
            View.OnClickListener {
                activity.startActivity(Intent(activity, HomeActivity::class.java))
//                intent.putExtra("nombre", citaMedicaModel.getNombre())
//                intent.putExtra("telefono", citaMedicaModel.getTelefono())
//                intent.putExtra("email", citaMedicaModel.getEmail())
//                intent.putExtra("foto", citaMedicaModel.getImagen())
//                activity.startActivity(intent)
            }
        )
    }

    override fun getItemCount(): Int {
        return citaMedicaModels.size
    }


    lateinit var binding: RvHistoriaClinicaLayoutBinding

    class CitaMedicaModelViewHolder(
        val binding: RvHistoriaClinicaLayoutBinding
    ) : RecyclerView.ViewHolder(binding.getRoot()) {

        fun bindConnection(citaMedicaModel: CitaMedicaModel) {
            this.binding.citaMedicaModelo=citaMedicaModel
        }
    }
}
