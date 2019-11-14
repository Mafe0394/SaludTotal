package com.wigilabs.saludtotal.entities

import com.google.gson.annotations.SerializedName


data class LoginModel(
        @SerializedName("PerfilUsuario")
        val perfilUsuario: String = "",
        @SerializedName("TipoEmpleadorId")
        val tipoEmpleadorId: String = "",
        @SerializedName("EmpleadorId")
        val empleadorId: String = "",
        @SerializedName("TipoDocumentoId")
        val tipoDocumentoId: String = "",
        @SerializedName("DocumentoId")
        val documentoId: String = "",
        @SerializedName("Clave")
        val clave: String = "",
        @SerializedName("aplicativo")
        val aplicativo: String = "APP"
)