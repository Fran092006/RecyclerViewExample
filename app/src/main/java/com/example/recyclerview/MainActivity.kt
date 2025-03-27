package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonaAdapter
    private lateinit var listaPersonas: ArrayList<Persona>
    private lateinit var apiService: ApiService
    private lateinit var toolbar: Toolbar // Agrega esta línea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicialización del RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView)
        listaPersonas = ArrayList()
        adapter = PersonaAdapter(listaPersonas)

        //Configuración de RetroFit para realizar las llamadas a la API
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://frandm.es:8080/LDTSHandlerSession/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        //Llamada a la función cargarUsuarios para obtener los datos de la API.
        cargarUsuarios()

        //Configura el RecyclerView con el LayoutManager y el adaptador
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    //Función cargarUsuarios() llamada anteriormente
    private fun cargarUsuarios() {
        apiService.getUsuarios().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    usuarios?.let {
                        listaPersonas.clear()
                        for (usuario in it) {
                            listaPersonas.add(Persona(usuario.Nombre, usuario.Apellidos, usuario.Usuario, usuario.Email))
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                Log.e("MainActivity", "Error en la llamada: ${t.message}")
            }
        })
    }
}

/*Ejemplo para hacer el recyclerView con datos estáticos
    listaPersonas = ArrayList()
    listaPersonas.add(Persona("Juan", "Pérez", "123456789", "juan.perez@example.com"))*/