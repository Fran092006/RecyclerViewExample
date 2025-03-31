package com.example.recyclerview

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("ArrayJson")
    fun getEventos(): Call<List<Evento>>
}
