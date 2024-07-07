package com.example.huachitos.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.huachitos.databinding.ActivityMainBinding
import com.example.huachitos.ui.adapter.AnimalAdapter
import com.example.huachitos.ui.viewmodel.AnimalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        // Inicializa el adaptador con una lista vacía
        val adapter = AnimalAdapter(emptyList()) { animal ->
            val intent = Intent(this, AnimalDetallesActivity::class.java).apply {
                putExtra("animal_id", animal.id)
            }
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Observa la lista de animales y actualiza el adaptador cuando cambie
        animalViewModel.animals.observe(this) { animals ->
            // Crea un nuevo adaptador con la nueva lista de animales
            val newAdapter = AnimalAdapter(animals) { animal ->
                val intent = Intent(this, AnimalDetallesActivity::class.java).apply {
                    putExtra("animal_id", animal.id)
                }
                startActivity(intent)
            }
            binding.recyclerView.adapter = newAdapter
        }

        animalViewModel.refreshAnimals()
    }
}
