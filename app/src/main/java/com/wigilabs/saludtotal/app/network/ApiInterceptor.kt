package com.wigilabs.saludtotal.app.network

import android.content.Context
import android.os.Build
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wigilabs.saludtotal.BuildConfig
import load
import loadEncryptedData
import okhttp3.*
import okio.Buffer


class ApiInterceptor(val context: Context) : Interceptor {



    override fun intercept(chain: Interceptor.Chain?): Response {
        if (chain == null) throw Throwable("chain is null")
        val response = when (chain.request().method()) {
            "POST","PUT" -> {
                val buffer = Buffer()
                chain.request().body()?.writeTo(buffer)
                val bufferUtf8 = buffer.readUtf8()
                val mediaType = MediaType.parse("application/json; charset=UTF-8")
                val jsonObject = JsonObject()
                jsonObject.add("data", JsonParser().parse(bufferUtf8))

                val requestBody = RequestBody.create(mediaType, jsonObject.toString())
                val request = chain.request().newBuilder()
                        .headers(chain.request().headers())
                        .header("Content-Type", requestBody.contentType().toString())
                        .header("Content-Length", requestBody.contentLength().toString())
                        .header("X-MC-SO", "android")
                        .header("Cache-Control", "no-cache")
                        .header("X-MC-SO-V", Build.VERSION.RELEASE)
                        .header("Authorization", context.loadEncryptedData("tkpS"))
                        .header("X-MC-SO-API", Build.VERSION.SDK_INT.toString())
                        .header("X-MC-SO-PHONE-F", Build.MANUFACTURER)
                        .header("X-MC-SO-PHONE-M", Build.MODEL)
                        .method(chain.request().method(), requestBody).build()
                chain.proceed(request)
            }
            else -> chain.proceed(chain.request().newBuilder()
                    .headers(chain.request().headers())
                    .header("X-MC-SO", "android")
                    .header("X-MC-SO-V", Build.VERSION.RELEASE)
                    .header("Cache-Control", "no-cache")
                    .header("X-MC-SO-API", Build.VERSION.SDK_INT.toString())
                    .header("X-MC-SO-PHONE-F", Build.MANUFACTURER)
                    .header("Authorization", context.loadEncryptedData("tkpS"))
                    .header("X-MC-SO-PHONE-M", Build.MODEL)
                    .header("X-MC-APP-V", BuildConfig.VERSION_NAME).build())
        }
        val jsonResponse = response.body()?.string() ?: ""
        return response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), jsonResponse)).build()
    }

}
