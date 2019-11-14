package com.wigilabs.saludtotal.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.net.Uri
import save


fun registerNetworkCallback(context: Context) {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val builder = NetworkRequest.Builder()
    connectivityManager!!.registerNetworkCallback(
        builder.build(),
        object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                // Network Available
                context.save("isNetworkConnected",true)
            }


            override fun onLost(network: Network) {
                // Network Not Available
                context.save("isNetworkConnected",false)
            }
        }
    )

}

fun openAppInGooglePlay(context: Context,packageApp: String){
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(
            "https://play.google.com/store/apps/details?id=$packageApp"
        )
        setPackage("com.android.vending")
    }
    context.startActivity(intent)
}
