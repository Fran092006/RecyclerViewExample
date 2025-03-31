package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para mostrar la lista de eventos en el RecyclerView
class PersonaAdapter(private val listaEventos: List<Evento>) :
    RecyclerView.Adapter<PersonaAdapter.EventoViewHolder>() {

    // ViewHolder que almacena las vistas de cada elemento del RecyclerView
    class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreEventoTextView: TextView = itemView.findViewById(R.id.nombreEventoTextView)
        val duracionTextView: TextView = itemView.findViewById(R.id.duracionTextView)
        val creadorTextView: TextView = itemView.findViewById(R.id.creadorTextView)
        val lugarTextView: TextView = itemView.findViewById(R.id.lugarTextView)
    }

    // Crea nuevas vistas (invocado por el LayoutManager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_persona, parent, false)
        return EventoViewHolder(itemView)
    }

    // Reemplaza el contenido de una vista (invocado por el LayoutManager)
    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val eventoActual = listaEventos[position]

        holder.nombreEventoTextView.text = "Evento: ${eventoActual.NombreEvento ?: "N/A"}"
        holder.duracionTextView.text = "Duración: ${eventoActual.Duración ?: "N/A"}"
        holder.creadorTextView.text = "Creador ID: ${eventoActual.Creador ?: "N/A"}"
        holder.lugarTextView.text = "Lugar: ${eventoActual.Lugar ?: "N/A"}"
    }

    // Devuelve el tamaño del dataset
    override fun getItemCount() = listaEventos.size
}
