package com.my.weather

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.my.weather.data.Cache
import com.my.weather.data.JsonHandler
import com.my.weather.models.City
import com.my.weather.models.WeatherItem
import com.my.weather.presentation.Weather
import com.my.weather.presentation.WeatherInfo
import com.my.weather.presentation.WeatherPageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLaunch(this)
        }
    }
}

@Composable
fun AppLaunch(context: Context) {
    val navController = rememberNavController()
    val jsonHandler = JsonHandler()
    val cache by lazy { Cache(context) }

    NavHost(
        navController = navController,
        startDestination = "weather_page"
    ) {
        composable("weather_page") {
            Weather(navController, WeatherPageViewModel(cache))
        }
        composable(
            route = "weather_info/{report}/{city}",
            arguments = listOf(
                navArgument("report") { type = NavType.StringType },
                navArgument("city") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val reportJson = backStackEntry.arguments?.getString("report")
            val cityJson = backStackEntry.arguments?.getString("city")
            val weatherReport = reportJson?.let { jsonHandler.decodeFromJson<WeatherItem>(it) }
            val city = cityJson?.let { jsonHandler.decodeFromJson<City>(it) }

            if (weatherReport != null && city != null) {
                WeatherInfo(navController, weatherReport, city)
            } else {
                Text("Invalid Data") // Fallback in case decoding fails
            }
        }
    }
}