package com.example.recyclerview

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("ConsultaGastos.php")
    fun getGastos(): Call<List<Gasto>>
}