package com.example.nasaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.databinding.ActivityMainBinding
import com.example.nasaapp.utils.NasaViewModelFactory
import com.example.nasaapp.utils.SharedPreferencesManager
import com.example.nasaapp.viewmodel.NasaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NasaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val sharedPreferencesManager = SharedPreferencesManager(applicationContext)

        viewModel = ViewModelProvider(this,
            NasaViewModelFactory(sharedPreferencesManager, this))[NasaViewModel::class.java]
        binding.nasaViewModel = viewModel
        binding.lifecycleOwner = this

        // Making the API call to fetch data
        viewModel.fetchImageData()
    }
}

