package com.wigilabs.saludtotal.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import load

abstract class BaseFragment : Fragment() {

    val baseActivity: BaseActivity by lazy {
        activity as BaseActivity
    }

    val ideasClient: SaludTotalClient by lazy {
        baseActivity.saludTotalClient
    }

/*
    val responseLogin: ResponseLoginModel by lazy {
        baseActivity.load(ResponseLoginModel::class.java)
    }
*/

    fun inflateView(inflater: LayoutInflater, container: ViewGroup?, @LayoutRes layout: Int): View {
        return inflater.inflate(layout, container, false)
    }


    fun checkError(jsonObject: JsonObject) = baseActivity.checkError(jsonObject)

    fun showProgressDialog() = baseActivity.showProgressDialog()

    fun dismissProgressDialog() = baseActivity.dismissProgressDialog()


}