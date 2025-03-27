package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adaptador para el RecyclerView que muestra la lista de personas
class PersonaAdapter(private val listaPersonas: List<Persona>) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    //Almacena las vistas de cada elemento del RecyclerView
    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreEventoTextView: TextView = itemView.findViewById(R.id.nombreEventoTextView)
        val duracionTextView: TextView = itemView.findViewById(R.id.duracionTextView)
        val creadorTextView: TextView = itemView.findViewById(R.id.creadorTextView)
        val lugarTextView: TextView = itemView.findViewById(R.id.lugarTextView)
    }

    //Crea nuevas vistas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_persona, parent, false)
        return PersonaViewHolder(itemView)
    }

    //Reemplaza el contenido de una vista (creada anteriormente con onCreateViewHolder)
    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val personaActual = listaPersonas[position]
        holder.nombreEventoTextView.text = "Nombre Evento: ${personaActual.nombreEvento ?: "N/A"}"
        holder.duracionTextView.text = "Duración: ${personaActual.duracion ?: "N/A"}"
        holder.creadorTextView.text = "Creador: ${personaActual.creador ?: "N/A"}"
        holder.lugarTextView.text = "Lugar: ${personaActual.lugar ?: "N/A"}"
    }

    //Devuelve el tamaño del dataset
    override fun getItemCount() = listaPersonas.size
}