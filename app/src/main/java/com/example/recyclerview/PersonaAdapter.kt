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
        val usuarioTextView: TextView = itemView.findViewById(R.id.usuarioTextView)//Coge el texto de nombre que hay en el layout item_persona
        val gastoTotalTextView: TextView = itemView.findViewById(R.id.gastoTotalTextView)//Coge el texto de gastoTotal que hay en item_persona
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
        holder.usuarioTextView.text = "Usuario: ${personaActual.nombre}" //Muestra el formato que se verá con el nombre al lanzar la aplicación
        holder.gastoTotalTextView.text = "Gasto total: ${personaActual.telefono} €"//Muestra el formato que se verá con el gasto total al lanzar la aplicación
    }

    //Devuelve el tamaño del dataset
    override fun getItemCount() = listaPersonas.size
}