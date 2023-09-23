package com.example.nasaapp.repository

import android.util.Log
import com.example.nasaapp.model.NasaApiData
import com.example.nasaapp.utils.RetrofitInstance

class NasaApiRepo {

    //Fetching the data from the api
    suspend fun fetchData(): NasaApiData? {
        try {
            val response = RetrofitInstance.nasaApiService.getData()

            if (response.isSuccessful) {
                return response.body()
            }
        } catch (e: Exception) {
            Log.e("ExceptionOccurred", "Server Error: $e")
        }
        return null
    }
}