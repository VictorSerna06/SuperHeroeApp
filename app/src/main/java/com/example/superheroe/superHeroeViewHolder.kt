package com.example.superheroe

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroe.databinding.ItemSuperheroeBinding
import com.squareup.picasso.Picasso

class superHeroeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Inicializamos Binding
    private val binding = ItemSuperheroeBinding.bind(view)

    fun bind(superHeroeItemRespuesta: superHeroeItemRespuesta,itemSeleccionado:(String) -> Unit) {
        binding.tvNombreSuperheroe.text = superHeroeItemRespuesta.nombre

        //Picasso
        Picasso.get().load(superHeroeItemRespuesta.superHeroeImagen.url).into(binding.ivSuperheroe)

        // Acción al momento de pulsar una vista
        binding.root.setOnClickListener{itemSeleccionado(superHeroeItemRespuesta.superHeroeId)}
    }
}