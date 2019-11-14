package com.wigilabs.saludtotal.fragments.historiaClinica

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.databinding.HistoriaClinicaFragmentBinding

class HistoriaClinicaFragment : Fragment() {

    companion object {
        fun newInstance() =
            HistoriaClinicaFragment()
    }

    private lateinit var viewModel: HistoriaClinicaViewModel

    private lateinit var binding:HistoriaClinicaFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.historia_clinica_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoriaClinicaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
