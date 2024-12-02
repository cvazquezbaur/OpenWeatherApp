package com.mlb.weather.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlb.weather.data.OpenWeatherApi
import com.mlb.weather.models.WeatherItem
import com.mlb.weather.models.WeatherReport
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherPageViewModel : ViewModel() {
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val apiKey = "ea8f48805c0549b80e1f74e9c15f465e"
//    private val _weatherData = MutableLiveData<List<WeatherItem>>()
//    val weatherData: LiveData<List<WeatherItem>> = _weatherData
    private val _weatherData = mutableStateOf<List<WeatherItem>?>(null)
    val weatherData: State<List<WeatherItem>?> = _weatherData
    private val _zipCode = mutableStateOf<String?>(null)
    val zipCode: State<String?> = _zipCode
    private val _dayCount = MutableLiveData<Int>()
    val dayCount: LiveData<Int> = _dayCount

    fun getWeatherData(): List<WeatherItem>? {
        return weatherData.value
    }

    fun setZipCode(zipCode: String) {
        _zipCode.value = zipCode
    }

    fun getZipCode(): String? {
        return zipCode.value
    }

    fun callWeatherApi(zipCode: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(OpenWeatherApi::class.java)
        val call = api.getForecastByZip(zip = "$zipCode,us", apiKey = apiKey)
        call.enqueue(object : retrofit2.Callback<WeatherReport> {
            override fun onResponse(
                call: Call<WeatherReport>,
                response: retrofit2.Response<WeatherReport>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    _weatherData.value = weatherResponse?.list
                    println("Weather data fetched successfully")
                } else {
                    println("Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<WeatherReport>, t: Throwable) {
                println("Failed to fetch weather data: ${t.message}")
            }
        })
    }
}