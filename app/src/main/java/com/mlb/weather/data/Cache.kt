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

    companion object {
        private const val TIMESTAMP_SUFFIX = "_timestamp"
        private const val CACHE_EXPIRATION = 3600_000L // 1 hour in milliseconds
    }

    // Save weather data
    fun saveWeather(zipCode: String?, weather: List<WeatherItem>?) {
        val json = gson.toJson(weather)
        zipCode?.let {
            prefs.edit()
                .putString(it, json)
                .putLong(it + TIMESTAMP_SUFFIX, System.currentTimeMillis())
                .apply()
        }
    }

    // Retrieve weather data
    fun getWeather(zipCode: String): WeatherItem? {
        val json = prefs.getString(zipCode, null)
        return json?.let {
            gson.fromJson(it, object : TypeToken<WeatherItem>() {}.type)
        }
    }

    fun getCity(zipCode: String): City? {
        val timestamp = prefs.getLong(zipCode + TIMESTAMP_SUFFIX, 0L)
        if (System.currentTimeMillis() - timestamp > CACHE_EXPIRATION) {
            // Cache expired
            removeCity(zipCode)
            return null
        }
        val json = prefs.getString(zipCode, null)
        return json?.let {
            try {
                gson.fromJson(it, object : TypeToken<List<WeatherItem>>() {}.type)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun saveCity(zipCode: String?, city: City?) {
        val json = gson.toJson(city)
        zipCode?.let {
            prefs.edit()
                .putString(it, json)
                .putLong(it + TIMESTAMP_SUFFIX, System.currentTimeMillis())
                .apply()
        }
    }

    fun removeCity(zipCode: String) {
        prefs.edit()
            .remove(zipCode)
            .remove(zipCode + TIMESTAMP_SUFFIX)
            .apply()
    }

    // Clear old weather data
    fun clearCache() {
        prefs.edit().clear().apply()
    }
}
