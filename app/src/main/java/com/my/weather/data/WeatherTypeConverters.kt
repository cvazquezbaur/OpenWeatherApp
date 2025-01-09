package com.my.weather.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.my.weather.models.City
import com.my.weather.models.WeatherItem

class WeatherTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherItemList(list: List<WeatherItem>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toWeatherItemList(json: String): List<WeatherItem> {
        val type = object : TypeToken<List<WeatherItem>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromCity(city: City): String {
        return gson.toJson(city)
    }

    @TypeConverter
    fun toCity(json: String): City {
        return gson.fromJson(json, City::class.java)
    }
}