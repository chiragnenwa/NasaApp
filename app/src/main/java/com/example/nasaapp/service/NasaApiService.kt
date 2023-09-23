package com.example.nasaapp.service

import com.example.nasaapp.model.NasaApiData
import retrofit2.Response
import retrofit2.http.GET

interface NasaApiService {
    @GET("apod?api_key=rKcYyzToRoPe2DAgY1XSYrqXF2ro67SaLPj4drex")
    suspend fun getData(): Response<NasaApiData>
}