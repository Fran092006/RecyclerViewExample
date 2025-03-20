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

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonaAdapter
    private lateinit var listaPersonas: ArrayList<Persona>
    private lateinit var apiService: ApiService

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
            .baseUrl("https://server.samuelgd.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        //Llamada a la función cargarGastos para obtener los datos de la API.
        cargarGastos()

        //Configura el RecyclerView con el LayoutManager y el adaptador
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    //Función cargarGastos() llamada anteriormente
    private fun cargarGastos() {
        apiService.getGastos().enqueue(object : Callback<List<Gasto>> {
            override fun onResponse(call: Call<List<Gasto>>, response: Response<List<Gasto>>) {
                if (response.isSuccessful) {
                    val gastos = response.body()
                    gastos?.let {
                        listaPersonas.clear()
                        for (gasto in it) {
                            val partesUsuario = gasto.Usuario.split(": ")
                            val nombreUsuario = partesUsuario.getOrNull(1) ?: "Usuario Desconocido"
                            val partesGasto = gasto.GastoTotal.split(" ")
                            val gastoNumero = partesGasto.getOrNull(0) ?: "0"
                            listaPersonas.add(Persona(nombreUsuario, "", gastoNumero, ""))
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Gasto>>, t: Throwable) {
                Log.e("MainActivity", "Error en la llamada: ${t.message}")
            }
        })
    }
}
/*Ejemplo para hacer el recyclerView con datos estáticos
    listaPersonas = ArrayList()
    listaPersonas.add(Persona("Juan", "Pérez", "123456789", "juan.perez@example.com"))*/