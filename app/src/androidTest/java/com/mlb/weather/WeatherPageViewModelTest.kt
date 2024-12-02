package com.mlb.weather

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import com.mlb.weather.data.Cache
import com.mlb.weather.models.WeatherItem
import com.mlb.weather.presentation.WeatherPageViewModel
import kotlinx.coroutines.test.runTest

class WeatherPageViewModelTest {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val cache = Cache(appContext)
    private val viewModel = WeatherPageViewModel(cache)


    @Test
    fun fetchWeatherData() = runTest {
        viewModel.callWeatherApi("87505")
        val weatherData = viewModel.getWeatherData()
        assertNotNull(weatherData)
    }

    @Test
    fun testCityName() {
        val cityName = "Spokane"
        viewModel.callWeatherApi("99202")
        val city = viewModel.city.value
        assertEquals(cityName, city?.name)
    }
}