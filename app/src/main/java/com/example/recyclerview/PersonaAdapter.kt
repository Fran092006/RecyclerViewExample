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
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val apellidosTextView: TextView = itemView.findViewById(R.id.apellidosTextView)
        val usuarioTextView: TextView = itemView.findViewById(R.id.usuarioTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.apellidosTextView)
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
        holder.nombreTextView.text = "Nombre: ${personaActual.nombre}"
        holder.apellidosTextView.text = "Apellidos: ${personaActual.apellidos}"
        holder.usuarioTextView.text = "Usuario: ${personaActual.telefono}" // Nota: 'telefono' en Persona corresponde a 'Usuario' en JSON
        holder.emailTextView.text = "Email: ${personaActual.email}"
    }

    //Devuelve el tamaño del dataset
    override fun getItemCount() = listaPersonas.size
}