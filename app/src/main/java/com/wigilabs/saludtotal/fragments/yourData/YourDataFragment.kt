package com.wigilabs.saludtotal.fragments.yourData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.wigilabs.saludtotal.R

class YourDataFragment : Fragment() {

    private lateinit var yourDataViewModel: YourDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        yourDataViewModel =
            ViewModelProviders.of(this).get(YourDataViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_your_data, container, false)
        return root
    }
}