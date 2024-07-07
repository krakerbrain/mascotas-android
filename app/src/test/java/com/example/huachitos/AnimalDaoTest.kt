package com.example.huachitos


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.huachitos.data.local.AnimalDao
import com.example.huachitos.data.local.AppDatabase
import com.example.huachitos.data.model.Animal
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimalDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var animalDao: AnimalDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        animalDao = database.animalDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testInsertAndGetAnimal() = runBlocking {
        val animal = Animal(
            id = 1,
            nombre = "Charlie",
            tipo = "Perro",
            color = "#F65B2A",
            edad = "1 Año",
            estado = "adopcion",
            genero = "hembra",
            desc_fisica = "Es una cachorra cafe",
            desc_personalidad = "Es muy juguetona, alegre y sociable.",
            desc_adicional = "Si estas interesado llena el formulario de pre adopción.",
            esterilizado = true,
            vacunas = 1,
            imagen = "https://huachitos.cl/storage/animal-images/charlie-charlie-1717449489.png",
            equipo = "Privado",
            region = "RM",
            comuna = "La Reina"
        )
        animalDao.insertAll(listOf(animal))

        val loaded = animalDao.getAnimalById(1)
        Assert.assertEquals(loaded.value?.nombre, "Charlie")
    }
}
