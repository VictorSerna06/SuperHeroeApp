package com.example.superheroe

import com.google.gson.annotations.SerializedName

data class RespuestaInformacionSuperHeroe(
    @SerializedName("name") val nombre: String,
    @SerializedName("powerstats") val estadisticas: RespuestaEstadisticas,
    @SerializedName("image") val imagen: RespuestaSuperHeroeImagen,
    @SerializedName("biography") val biografia: RespuestaBiaografia
)

data class RespuestaEstadisticas(
    @SerializedName("intelligence") val inteligencia: String,
    @SerializedName("strength") val fuerza: String,
    @SerializedName("speed") val velocidad: String,
    @SerializedName("durability") val durabilidad: String,
    @SerializedName("power") val poder: String,
    @SerializedName("combat") val combate: String
)

data class RespuestaSuperHeroeImagen(@SerializedName("url") val url: String)

data class RespuestaBiaografia(
    @SerializedName("full-name") val nombreCompleto: String,
    @SerializedName("first-appearance") val primeraAparicion:String,
    @SerializedName("publisher") val editora: String
)