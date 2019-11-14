package com.wigilabs.saludtotal.base

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.wigilabs.saludtotal.BuildConfig
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.app.network.ApiInterceptor
import com.wigilabs.saludtotal.app.network.SaludTotalNetwork
import com.wigilabs.saludtotal.entities.LoginModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.wigilabs.saludtotal.entities.CiudadesPacModel


class SaludTotalClient(val context: Context) {


    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor(context))
            .addInterceptor (
                LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Api Request")
                    .response("Api Response")
                    .build())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).build()

    private val apiInterface: SaludTotalNetwork = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(SaludTotalNetwork::class.java)


    private fun rxConfig(jsonObjectObservable: Observable<JsonObject>): Observable<JsonObject> {
        return jsonObjectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(this::getError)
    }


    private fun getError(throwable: Throwable): JsonObject {
        val error = "${throwable.message} Class: ${throwable.javaClass.name}"
        Log.e("Api Error", error, throwable)
        val jsonObject = JsonObject()
        jsonObject.addProperty("error", 1)
        val errorMenssage = when (throwable) {
            is UnknownHostException -> if (BuildConfig.DEBUG) error else context.getString(R.string.error_no_internet)
            else -> if (BuildConfig.DEBUG) error else context.getString(R.string.error_inesperado_servicios)
        }
        jsonObject.addProperty("response", errorMenssage)
        return jsonObject
    }

    fun login(loginModel: LoginModel): Observable<JsonObject> = rxConfig(apiInterface.login(loginModel.perfilUsuario,
        loginModel.tipoEmpleadorId,loginModel.empleadorId,loginModel.tipoDocumentoId,loginModel.documentoId
        ,loginModel.clave,loginModel.aplicativo))

    fun checkToken(token: String, application: String): Observable<JsonObject> = rxConfig(
        apiInterface.checkToken("",""))

    fun getTokenAfiliado(token: String, application: String): Observable<JsonObject> = rxConfig(
        apiInterface.getTokenAfiliado("",""))

    fun getTokenComoAfiliarme(token: String, application: String): Observable<JsonObject> = rxConfig(
        apiInterface.getTokenComoAfiliarme(token,application))

    fun registrarSolicitud(token: String, application: String): Observable<JsonObject> = rxConfig(
        apiInterface.registrarSolicitud("",""))

    fun getCitiesPac(): Observable<JsonObject> = rxConfig(
        apiInterface.getCitiesPac())

}