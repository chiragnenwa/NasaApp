package com.example.nasaapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.viewmodel.NasaViewModel

class NasaViewModelFactory(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NasaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NasaViewModel(sharedPreferencesManager,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}