package com.mlb.weather.data

import com.mlb.weather.models.WeatherReport
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("forecast")
    fun getForecastByZip(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial" // Use "metric" for Celcius
    ): Call<WeatherReport>
}