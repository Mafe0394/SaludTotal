package com.wigilabs.saludtotal.app.network


import com.google.gson.JsonObject
import com.wigilabs.saludtotal.entities.CiudadesPacModel
import com.wigilabs.saludtotal.entities.LoginModel
import io.reactivex.Observable
import retrofit2.http.*



/**
 * interface que contiene las peticiones hacia el
 * servidor el cual retorna
 * el datos puro o una clase POJO GSON
 */
interface SaludTotalNetwork {


    @GET("ApiSeguridad/api/LogIn/")
    fun login(@Query("PerfilUsuario") perfilUsuario: String,
              @Query("TipoEmpleadorId") tipoEmpleadorId: String,
              @Query("EmpleadorId") empleadorId: String,
              @Query("TipoDocumentoId") tipoDocumentoId: String,
              @Query("DocumentoId") documentoId: String,
              @Query("Clave") clave: String,
              @Query("aplicativo") aplicativo: String): Observable<JsonObject>

    @GET("ApiSeguridad/api/ValidaToken/")
    fun checkToken(@Query("token") token: String,
              @Query("aplicativo") aplicativo: String): Observable<JsonObject>

    @GET("STAPI_AfiliadosPBS/api/Token/")
    fun getTokenAfiliado(@Query("Token") token: String,
                   @Query("Origen") aplicativo: String): Observable<JsonObject>

    @GET("STAPI_ComoAfiliarme/api/Token/GetToken/")
    fun getTokenComoAfiliarme(@Query("Token") token: String,
                         @Query("Origen") aplicativo: String): Observable<JsonObject>

    @GET("STAPI_ComoAfiliarme/api/Registro/RegistrarSolicitud/")
    fun registrarSolicitud(@Query("Token") token: String,
                              @Query("Origen") aplicativo: String): Observable<JsonObject>

    @GET("STAPI_ComoAfiliarme/api/Ciudades/TraerCiudadesPac/")
    fun getCitiesPac(): Observable<JsonObject>

}












