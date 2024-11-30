package com.mlb.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlb.weather.models.WeatherReport
import kotlinx.coroutines.launch

class WeatherPageViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherReport>()
    val weatherData: LiveData<WeatherReport> = _weatherData
    private val _zipCode = MutableLiveData<String>()
    val zipCode: LiveData<String> = _zipCode

    fun setWeatherData(data: WeatherReport) {
        _weatherData.value = data
    }

    fun getWeatherData(): WeatherReport? {
        return _weatherData.value
    }

    fun callWeatherApi(zipCode: String) {
        viewModelScope.launch {

        }
    }
}