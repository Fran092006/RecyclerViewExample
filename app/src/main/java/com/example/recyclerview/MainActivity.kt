package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonaAdapter
    private lateinit var listaEventos: ArrayList<Evento>
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización del RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView)
        listaEventos = ArrayList()
        adapter = PersonaAdapter(listaEventos)

        // Configuración de Retrofit para realizar las llamadas a la API
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

        // Llamada a la función para obtener los datos de la API.
        cargarEventos()

        // Configura el RecyclerView con el LayoutManager y el adaptador
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    // Función para obtener los eventos de la API
    private fun cargarEventos() {
        apiService.getEventos().enqueue(object : Callback<List<Evento>> {
            override fun onResponse(call: Call<List<Evento>>, response: Response<List<Evento>>) {
                if (response.isSuccessful) {
                    val eventos = response.body()
                    eventos?.let {
                        listaEventos.clear()
                        listaEventos.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Evento>>, t: Throwable) {
                Log.e("MainActivity", "Error en la llamada: ${t.message}")
            }
        })
    }
}
