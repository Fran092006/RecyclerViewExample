package com.example.recyclerview

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("ArrayJson")
    fun getUsuarios(): Call<List<Usuario>>
}