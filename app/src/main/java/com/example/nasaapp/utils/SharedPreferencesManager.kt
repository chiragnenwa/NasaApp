package com.example.nasaapp.utils

import android.content.Context
import com.example.nasaapp.model.NasaApiData

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveNasaImageData(imageData: NasaApiData) {
        val editor = sharedPreferences.edit()
        editor.putString("imageUrl", imageData.url)
        editor.putString("imageTitle", imageData.title)
        editor.putString("imageExplanation", imageData.explanation)
        editor.putString("date", imageData.date)
        editor.putString("media_type", imageData.media_type)
        editor.putString("copyright", imageData.copyright)
        editor.apply()
    }

    fun getNasaImageData(): NasaApiData {
        return NasaApiData(
            sharedPreferences.getString("imageUrl", "").toString(),
            sharedPreferences.getString("imageTitle", "").toString(),
            sharedPreferences.getString("imageExplanation", "").toString(),
            sharedPreferences.getString("date", "").toString(),
            sharedPreferences.getString("media_type", "").toString(),
            sharedPreferences.getString("copyright", "").toString(),
            )
    }

    fun getStoredDate(): String{
        return sharedPreferences.getString("date", "").toString()
    }
}
