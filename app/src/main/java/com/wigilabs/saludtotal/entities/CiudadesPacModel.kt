package com.wigilabs.saludtotal.entities

import com.google.gson.annotations.SerializedName


data class CiudadesPacModel(
        var ciudades : ArrayList<Ciudad> = ArrayList()
){
        class Ciudad(
                @SerializedName("CiudadId")
                val ciudadId: String = "",
                @SerializedName("Nombre")
                val nombre: String = "",
                @SerializedName("Departamento")
                val departamento: String = "",
                @SerializedName("TipoUbicacionId")
                val tipoUbicacionId: String = "",
                @SerializedName("RedServiciosPos")
                val redServiciosPos: String = "",
                @SerializedName("EpsSucursalId")
                val epsSucursalId: String = "",
                @SerializedName("CodDepartamento")
                val codDepartamento: String = "",
                @SerializedName("CodMunicipio")
                val codMunicipio: String = "",
                @SerializedName("NombreMostrar")
                val nombreMostrar: String = "",
                @SerializedName("cobertura")
                val cobertura: String = ""
        )
}