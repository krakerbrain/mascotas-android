package com.example.huachitos.data.repository

import androidx.lifecycle.LiveData
import com.example.huachitos.data.local.AnimalDao
import com.example.huachitos.data.model.Animal
import com.example.huachitos.data.remote.AnimalApiService

/**
 * Repositorio para la API de animales
 */
class AnimalRepository(
    private val apiService: AnimalApiService,
    private val animalDao: AnimalDao
) {
    val animals: LiveData<List<Animal>> = animalDao.getAllAnimals()

    suspend fun refreshAnimals() {
        val animalsFromApi = apiService.getAnimals()
        animalDao.insertAll(animalsFromApi)
    }

    suspend fun getAnimalDetails(id: Int): Animal {
        return apiService.getAnimalDetails(id)
    }
}
