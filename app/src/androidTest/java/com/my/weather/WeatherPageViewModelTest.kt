package com.my.weather

import android.content.Context
import com.my.weather.data.Cache
import org.junit.Test
import org.junit.Assert.*
import com.my.weather.presentation.WeatherPageViewModel
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherPageViewModelTest {
    private lateinit var viewModel: WeatherPageViewModel
    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var cache: Cache

    @Before
    fun setUp() {
        context = Mockito.mock(Context::class.java)
        cache = Mockito.mock(Cache::class.java)
        viewModel = WeatherPageViewModel(cache)
    }


    @Test
    fun fetchWeatherData() {
        viewModel.callWeatherApi(Mockito.mock())
        assertNotNull(viewModel.weatherData)
    }

    @Test
    fun testCityName() {
        val cityName = "Spokane"
        viewModel.callWeatherApi("99202")
        val city = viewModel.city.value
        assertEquals(cityName, city?.name)
    }
}