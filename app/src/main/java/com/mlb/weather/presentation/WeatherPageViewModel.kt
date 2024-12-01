package com.mlb.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlb.weather.models.WeatherReport
import kotlinx.coroutines.launch

class WeatherPageViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<List<WeatherReport>>()
    val weatherData: LiveData<List<WeatherReport>> = _weatherData
    private val _zipCode = MutableLiveData<String>()
    val zipCode: LiveData<String> = _zipCode
    private val _dayCount = MutableLiveData<Int>()
    val dayCount: LiveData<Int> = _dayCount

    fun getWeatherData(): List<WeatherReport>? {
        return weatherData.value
    }

    fun setZipCode(zipCode: String) {
        _zipCode.value = zipCode
    }

    fun getZipCode(): String? {
        return zipCode.value
    }

    fun setDayCount(dayCount: Int) {
        _dayCount.value = dayCount
    }

    fun getDayCount(): Int? {
        return dayCount.value
    }

    fun callWeatherApi(zipCode: String) {
        viewModelScope.launch {

        }
    }
}