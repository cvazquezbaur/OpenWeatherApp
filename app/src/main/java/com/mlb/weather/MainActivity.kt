package com.mlb.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlb.weather.presentation.Weather
import com.mlb.weather.presentation.WeatherInfo
import com.mlb.weather.ui.theme.MLB_WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLaunch()
        }
    }
}

@Composable
fun AppLaunch() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "weather_page"
    ) {
        composable("weather_page") {
            Weather(navController)
        }
        composable("weather_info") {
            WeatherInfo(navController)
        }
    }
}