package com.example.superheroe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class superHeroeAdapter(
    var listaSuperHeroe: List<superHeroeItemRespuesta> = emptyList(),
    private val itemSeleccionado: (String) -> Unit
) :
    RecyclerView.Adapter<superHeroeViewHolder>() {

    //Actualizar la lista
    fun updateList(lista: List<superHeroeItemRespuesta>) {
        listaSuperHeroe = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): superHeroeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return superHeroeViewHolder(layoutInflater.inflate(R.layout.item_superheroe, parent, false))
    }

    // Devuelve el tamaño de la variable
    override fun getItemCount() = listaSuperHeroe.size


    override fun onBindViewHolder(holder: superHeroeViewHolder, position: Int) {
        val item = listaSuperHeroe[position]
        holder.bind(item,itemSeleccionado)
    }
}