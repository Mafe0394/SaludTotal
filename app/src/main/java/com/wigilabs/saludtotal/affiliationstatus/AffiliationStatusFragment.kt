package com.wigilabs.saludtotal.affiliationstatus

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseFragment
import com.wigilabs.saludtotal.entities.NotificationModel

class AffiliationStatusFragment : BaseFragment(), AffiliationStatusViewModel.Listener {


    companion object {
        fun newInstance() = AffiliationStatusFragment()
    }

    private lateinit var viewModel: AffiliationStatusViewModel

    private var rootView: View? = null

    var status: ArrayList<NotificationModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.affiliation_status_fragment, container,
                false)
            val rvStatus = rootView!!.findViewById<RecyclerView>(R.id.rv_status)
            rvStatus.layoutManager = LinearLayoutManager(baseActivity)
            rvStatus.adapter = viewModel.getRecyclerAdapter()
            loadAffiliationStatus()
        }
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AffiliationStatusViewModel::class.java)
        viewModel.listener = this
    }


    override fun loadAffiliationStatus() {
        status.add(
            NotificationModel("Kevin Serrano","CC. 42353453",
                "Cotizante","Activo")
        )
        status.add(
            NotificationModel("Juana De Arcos","CC. 3421212",
                "Beneficiario / Hija","Inactivo")
        )
        viewModel.setStatusInRecyclerAdapter(status)
    }

}
