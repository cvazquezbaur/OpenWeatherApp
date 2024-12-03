package com.mlb.weather.presentation

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.mlb.weather.data.Cache
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherPageViewModelTester {
    lateinit var viewModel: WeatherPageViewModel

    @Before
    fun setUp() {
        viewModel = Mockito.mock(WeatherPageViewModel::class.java)
    }

    @Test
    fun testFetchWeatherData() {
        viewModel.callWeatherApi("87505")
        println("Weather data fetched successfully: ${viewModel.weatherData}")
    }
}