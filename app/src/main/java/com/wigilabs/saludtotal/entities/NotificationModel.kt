package com.wigilabs.saludtotal.entities

import com.google.gson.annotations.SerializedName


data class NotificationModel(
        @SerializedName("PerfilUsuario")
        val hour: String = "",
        @SerializedName("TipoEmpleadorId")
        val type: String = "",
        @SerializedName("EmpleadorId")
        val date: String = "",
        @SerializedName("TipoDocumentoId")
        val place: String = ""
)