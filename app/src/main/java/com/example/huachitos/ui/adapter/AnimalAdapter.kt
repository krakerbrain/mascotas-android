package com.example.huachitos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.huachitos.R
import com.example.huachitos.data.model.Animal
import com.squareup.picasso.Picasso

/**
 * Adaptador para la lista de animales. Proporciona una vista personalizada para cada animal en la lista.
 */
class AnimalAdapter(private val animalList: List<Animal>, private val onItemClick: (Animal) -> Unit) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val animalImage: ImageView = itemView.findViewById(R.id.animalImage)
        private val animalName: TextView = itemView.findViewById(R.id.animalName)
        private val animalBreed: TextView = itemView.findViewById(R.id.animalBreed)
        private val animalAge: TextView = itemView.findViewById(R.id.animalAge)
        private val animalGender: ImageView = itemView.findViewById(R.id.animalGender)

        fun bind(animal: Animal) {
            animalName.text = animal.nombre
            animalBreed.text = animal.tipo // Assuming 'tipo' is breed
            animalAge.text = animal.edad
            // Cargar imagen usando Picasso
            Picasso.get().load(animal.imagen).into(animalImage)


            // Set icono del género
            val genderIcon = if (animal.genero == "hembra") R.drawable.ic_female else R.drawable.ic_male
            animalGender.setImageResource(genderIcon)

            itemView.setOnClickListener { onItemClick(animal) }
        }
    }
}
