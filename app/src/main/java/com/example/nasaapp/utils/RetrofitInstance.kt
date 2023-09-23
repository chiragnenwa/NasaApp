package com.example.nasaapp.utils

import com.example.nasaapp.service.NasaApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.nasa.gov/planetary/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val nasaApiService: NasaApiService = retrofit.create(NasaApiService::class.java)

}
