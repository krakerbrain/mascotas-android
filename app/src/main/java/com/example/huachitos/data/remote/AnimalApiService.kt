package com.example.huachitos.data.remote

import com.example.huachitos.data.model.Animal
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface para las solicitudes a la API
 */

interface AnimalApiService {
    @GET("animales")
    suspend fun getAnimals(): List<Animal>

    @GET("animales/{id}")
    suspend fun getAnimalDetails(@Path("id") id: Int): Animal
}
