package com.example.huachitos.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.huachitos.data.local.AppDatabase
import com.example.huachitos.data.model.Animal
import com.example.huachitos.data.remote.AnimalApiService
import com.example.huachitos.data.repository.AnimalRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ViewModel para la interfaz de usuario.
 *
 */
class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AnimalRepository

    val animals: LiveData<List<Animal>>

    init {
        val animalDao = AppDatabase.getDatabase(application).animalDao()
        val apiService = Retrofit.Builder()
            .baseUrl("https://caso-perritos-adopcion-c74apk0pl-talento-projects.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalApiService::class.java)
        repository = AnimalRepository(apiService, animalDao)
        animals = repository.animals
    }

    fun refreshAnimals() = viewModelScope.launch {
        repository.refreshAnimals()
    }

    fun getAnimalDetails(id: Int): LiveData<Animal> {
        val liveData = MutableLiveData<Animal>()
        viewModelScope.launch {
            liveData.value = repository.getAnimalDetails(id)
        }
        return liveData
    }
}
