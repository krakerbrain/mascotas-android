package com.example.huachitos.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.huachitos.data.model.Animal
import com.example.huachitos.databinding.ActivityAnimalDetallesBinding
import com.example.huachitos.ui.viewmodel.AnimalViewModel
import com.squareup.picasso.Picasso

class AnimalDetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetallesBinding
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID del animal del intent o finalizar la actividad si no se proporciona el ID
        val animalId = intent.getIntExtra("animal_id", -1)
        if (animalId == -1) finish()

        // Inicializar el ViewModel para la actividad
        animalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        // Observar los detalles del animal y actualizar la interfaz de usuario
        animalViewModel.getAnimalDetails(animalId).observe(this) { animal ->
            if (animal != null) {
                binding.animalName.text = animal.nombre
                binding.animalDescription.text = animal.desc_fisica
                binding.animalAge.text = animal.edad
                binding.animalGender.text = animal.genero
                binding.animalBreed.text = animal.tipo
                binding.animalLocation.text = animal.comuna
                Picasso.get().load(animal.imagen).into(binding.animalImage)

                binding.emailButton.setOnClickListener {
                    sendEmail(animal)
                }
            }
        }
    }

    // Función para enviar un correo electrónico con detalles del animal
    private fun sendEmail(animal: Animal) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, "ventas_botxcamps@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "Consulta ${animal.nombre} id ${animal.id}")
            putExtra(
                Intent.EXTRA_TEXT,
                "Hola\n\nVi el animal ${animal.nombre} y me gustaría adoptarlo. Pueden contactarme a este correo o al siguiente número _________\n\nQuedo atent@."
            )
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
