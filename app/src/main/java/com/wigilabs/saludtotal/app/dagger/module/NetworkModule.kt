package com.wigilabs.saludtotal.app.dagger.module

import android.content.Context
import android.util.Log
import com.wigilabs.saludtotal.app.dagger.AppScope
import com.google.gson.Gson
import com.wigilabs.saludtotal.BuildConfig
import com.wigilabs.saludtotal.app.dagger.qualifiers.ApplicationContext
import com.wigilabs.saludtotal.base.SaludTotalClient
import com.wigilabs.saludtotal.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Module
/***
 * Modulo encargado de la conectividad
 * incopora retorfit con la url base
 */
class NetworkModule {
    @AppScope
    @Provides
    fun cache(@ApplicationContext context: Context): Cache {
        return Cache(File(context.cacheDir, Constants.HTTP_CACHE_DIR),
                Constants.HTTP_CACHE_SIZE.toLong())
    }

    @AppScope
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }

    @AppScope
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Log.d("HTTPError ", message) }
        return logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @AppScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @AppScope
    @Provides
    fun network(retrofit: Retrofit): SaludTotalClient {
        return retrofit.create(SaludTotalClient::class.java)
    }
}
