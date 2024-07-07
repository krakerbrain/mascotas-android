package com.example.huachitos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase Animal para la base de datos Room
 */

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey val id: Int,
    val nombre: String,
    val tipo: String,
    val color: String,
    val edad: String,
    val estado: String,
    val genero: String,
    val desc_fisica: String,
    val desc_personalidad: String,
    val desc_adicional: String,
    val esterilizado: Boolean,
    val vacunas: Int,
    val imagen: String,
    val equipo: String,
    val region: String,
    val comuna: String
)
