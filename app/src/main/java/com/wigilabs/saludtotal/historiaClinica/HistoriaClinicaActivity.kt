package com.wigilabs.saludtotal.historiaClinica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityHistoriaClinicaBinding
import com.wigilabs.saludtotal.entities.CitaMedicaModel

class HistoriaClinicaActivity : BaseActivity() {

    private lateinit var binding:ActivityHistoriaClinicaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_historia_clinica)
        startActionBar(resources.getString(R.string.home_lb_historia_clinica))

        createCitaMedicaModels()
        
    }

    fun createCitaMedicaModels():ArrayList<CitaMedicaModel>{
        var a=ArrayList<CitaMedicaModel>()
        a.add(CitaMedicaModel())
        a.add(CitaMedicaModel())
        return a
    }
}
