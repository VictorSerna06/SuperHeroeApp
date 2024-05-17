package com.example.superheroe

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // Consumo de API rest "nombre"
    @GET("/api/10229233666327556/search/{nombre}")
    // Corrutina
    suspend fun getSuperHeroes(@Path("nombre") nombreSuperHeroe: String): Response<superHeroeDataRespuesta>

    // Consumo de API rest "id"
    @GET("/api/10229233666327556/{id}")
    // Corrutina
    suspend fun getSuperHeroe(@Path("id") superHeroeId: String): Response<RespuestaInformacionSuperHeroe>

}