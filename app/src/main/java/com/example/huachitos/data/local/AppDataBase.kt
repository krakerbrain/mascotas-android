package com.example.huachitos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.huachitos.data.model.Animal

/**
 *  Se define la base de datos de Room
 */
@Database(entities = [Animal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "animal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
