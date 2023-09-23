package com.example.nasaapp.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapp.repository.NasaApiRepo
import com.example.nasaapp.utils.SharedPreferencesManager
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NasaViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    context: Context,
) : ViewModel() {
    private val nasaApiRepo = NasaApiRepo()
    private val contextReference: WeakReference<Context> = WeakReference(context)

    private val _imageUrl = MutableLiveData<String>()
    private val _imageTitle = MutableLiveData<String>()
    private val _imageExplanation = MutableLiveData<String>()
    private val _date = MutableLiveData<String>()
    private val _copyright = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()

    val imageUrl: LiveData<String>
        get() = _imageUrl
    val imageTitle: LiveData<String>
        get() = _imageTitle
    val imageExplanation: LiveData<String>
        get() = _imageExplanation
    val date: LiveData<String>
        get() = _date
    val copyright: LiveData<String>
        get() = _copyright
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // Making the API call and update LiveData and storing in the shared preferences
    fun fetchImageData() {
        viewModelScope.launch {
            //Stored Date
            val storedDate = sharedPreferencesManager.getStoredDate()

            //Comparing the current date with stored date
            if (storedDate != getCurrentDate() ) {

                //Checking the internet connectivity is available
                if(checkForInternet()){
                    _isLoading.value = true

                    //Making the api call to fetch the image data
                    val data = nasaApiRepo.fetchData()
                    if (data != null) {
                        //Storing the fetched data into the storage using shared preferences
                        sharedPreferencesManager.saveNasaImageData(data)
                        _imageUrl.value = data.url
                        _imageTitle.value = data.title
                        _imageExplanation.value = data.explanation
                        _date.value = data.date
                        _copyright.value = data.copyright
                    } else {
                        Log.e("APICALLERROR", "Data is Null")
                    }
                    _isLoading.value = false
                }
                else{
                    //Showing the dialog box to turn the Connectivity
                    showNoInternetDialog()
                }

            } else {
                //Setting the data from the Shared Preference
                setNasaImageDataFromSharedPreferences()
            }
        }
    }

    // Function to set data from SharedPreferences
    private fun setNasaImageDataFromSharedPreferences() {
        val data = sharedPreferencesManager.getNasaImageData()
        _imageUrl.value = data.url
        _imageTitle.value = data.title
        _imageExplanation.value = data.explanation
        _date.value = data.date
        _copyright.value = data.copyright
    }

    //Function to get the current date
    private fun getCurrentDate(): String {
        val dateFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDate.now()
        return currentDate.format(dateFormat)
    }

    // Function to check if the internet connection is available
    private fun checkForInternet(): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            contextReference.get()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }

    // Function to show a dialog for no internet and provide a settings option
    private fun showNoInternetDialog() {
        val context = contextReference.get() // Retrieving the context from WeakReference
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.apply {
            setTitle("No Internet Connection")
            setMessage("Please enable Wi-Fi or mobile data.")
            setPositiveButton("Settings") { _, _ ->
                // Open network settings when the "Settings" button is clicked
                val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                context?.startActivity(intent)
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}