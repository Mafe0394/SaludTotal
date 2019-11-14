package com.wigilabs.saludtotal.fragments.directory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wigilabs.saludtotal.R

class DirectoryFragment : Fragment() {

    companion object {
        fun newInstance() = DirectoryFragment()
    }

    private lateinit var viewModel: DirectoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.directory_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DirectoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
