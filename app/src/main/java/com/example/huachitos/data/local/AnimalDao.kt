package com.example.huachitos.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.huachitos.data.model.Animal

/**
 *  Se define el DAO para la tabla Animal
 */
@Dao
interface AnimalDao {

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animals: List<Animal>)

    @Query("SELECT * FROM animals WHERE id = :animalId")
    fun getAnimalById(animalId: Int): LiveData<Animal>
}