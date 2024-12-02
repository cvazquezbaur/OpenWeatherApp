package com.mlb.weather.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.mlb.weather.models.WeatherItem
import com.mlb.weather.models.WeatherReport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherInfo(navController: NavController, detailedWeatherReport: WeatherItem) {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text("Weather Report for ${detailedWeatherReport.}") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF7D5260),
                titleContentColor = Color.White
            )
        ) },
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding ->
    }
}