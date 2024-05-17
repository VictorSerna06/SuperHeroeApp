package com.example.superheroe

import com.google.gson.annotations.SerializedName

// Objeto principal que tiene la respuesta
data class superHeroeDataRespuesta(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<superHeroeItemRespuesta>
) {
}

// Lista de atributos "Id" y "Nombre"
data class superHeroeItemRespuesta(
    @SerializedName("id") val superHeroeId: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("image") val superHeroeImagen: superHeroeImagenRespuesta
)

// Objeto "Imagen"
data class superHeroeImagenRespuesta(
    @SerializedName("url") val url: String
)