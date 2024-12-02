package com.mlb.weather.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mlb.weather.models.City
import com.mlb.weather.models.WeatherItem

class Cache(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("weather_cache", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Save weather data
    fun saveWeather(cityName: String?, weather: List<WeatherItem>?) {
        val json = gson.toJson(weather)
        prefs.edit().putString(cityName, json).apply()
    }

    // Retrieve weather data
    fun getWeather(cityName: String): WeatherItem? {
        val json = prefs.getString(cityName, null)
        return json?.let {
            gson.fromJson(it, object : TypeToken<WeatherItem>() {}.type)
        }
    }

    fun getCity(cityName: String): City? {
        val json = prefs.getString(cityName, null)
        return json?.let {
            gson.fromJson(it, object : TypeToken<City>() {}.type)
        }
    }

    fun saveCity(cityName: String?, city: City?) {
        val json = gson.toJson(city)
        prefs.edit().putString(cityName, json).apply()
    }

    // Clear old weather data
    fun clearCache() {
        prefs.edit().clear().apply()
    }
}
