package com.my.weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.weather.data.Cache
import com.my.weather.data.OpenWeatherApi
import com.my.weather.models.City
import com.my.weather.models.WeatherItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherPageViewModel(cache: Cache) : ViewModel() {
    private val cache = cache
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val apiKey = "ea8f48805c0549b80e1f74e9c15f465e"

    private val _weatherReportState = MutableStateFlow<WeatherReportState>(WeatherReportState.Empty)
    val weatherReportState: StateFlow<WeatherReportState> = _weatherReportState

    private val _weatherData = MutableStateFlow<List<WeatherItem>?>(null)
    private val weatherData: StateFlow<List<WeatherItem>?> = _weatherData
    private val _city = MutableStateFlow<City?>(null)
    val city: StateFlow<City?> = _city
    private val _zipCode = MutableStateFlow<String?>(null)
    val zipCode: StateFlow<String?> = _zipCode
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(OpenWeatherApi::class.java)

    fun setZipCode(zipCode: String) {
        _zipCode.value = zipCode
    }

    fun callWeatherApi(zipCode: String) {
        viewModelScope.launch {
            try {
                cache.clearCache()
                val response = api.getForecastByZip(zip = "$zipCode,us", apiKey = apiKey).execute()
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    val weatherItems = weatherResponse?.list ?: emptyList()
                    val city = weatherResponse?.city

                    if (city != null) {
                        _weatherReportState.value = WeatherReportState.Loaded(
                            weatherItems = weatherItems,
                            city = city,
                            zip = zipCode
                        )
                        cache.saveWeather(zipCode, weatherItems)
                        cache.saveCity(zipCode, city)
                    }
                } else {
                    Log.e("didn't work", "didn't work")
                    _weatherReportState.value = WeatherReportState.Error("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                _weatherReportState.value = WeatherReportState.Error("Failed to fetch weather data: ${e.message}")
            }
        }
//        cache.clearCache()
//
//        val call = api.getForecastByZip(zip = "$zipCode,us", apiKey = apiKey)
//        // Clear old weather data for now. For future growth, will have this under a conditional that checks for network access
//        // and probably move over to a Room DB that I started implementing but ran out of time with.
//        call.enqueue(object : Callback<WeatherReport> {
//            override fun onResponse(
//                call: Call<WeatherReport>,
//                response: Response<WeatherReport>
//            ) {
//                if (response.isSuccessful) {
//                    val weatherResponse = response.body()
//
//                    _weatherData.value = weatherResponse?.list
//                    _city.value = weatherResponse?.city
//                    _weatherReportState.value = WeatherReportState.Loaded(
//                        weatherItems = weatherResponse?.list ?: emptyList(),
//                        city = weatherResponse?.city!!,
//                        zip = zipCode
//                    )
//                    cache.saveWeather(zipCode, weatherResponse?.list)
//                    cache.saveCity(zipCode, weatherResponse?.city)
//                    println("Weather data fetched successfully")
//                } else {
//                    println("Error: ${response.code()} - ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherReport>, t: Throwable) {
//                println("Failed to fetch weather data: ${t.message}")
//            }
//        })
    }

    fun checkCache(zipCode: String) {
        viewModelScope.launch {
            val city = cache.getCity(zipCode)
            if (city != null) {
                val weatherItems = cache.getWeather(zipCode)?.let { listOf(it) } ?: emptyList()
                _weatherReportState.value = WeatherReportState.Loaded(
                    weatherItems = weatherItems,
                    city = city,
                    zip = zipCode
                )
            } else {
                setZipCode(zipCode)
                callWeatherApi(zipCode)
            }
        }
    }

    sealed class WeatherReportState {
        object Empty : WeatherReportState()
        data class Loaded(
            val weatherItems: List<WeatherItem>,
            val city: City,
            val zip: String
        ) : WeatherReportState()
        data class Error(val message: String) : WeatherReportState()
    }
}