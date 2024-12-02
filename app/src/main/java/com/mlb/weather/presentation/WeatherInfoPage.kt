package com.mlb.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mlb.weather.models.City
import com.mlb.weather.models.WeatherItem
import com.mlb.weather.models.WeatherReport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherInfo(navController: NavController, weatherReport: WeatherItem, city: City) {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text("Detailed Weather Report for ${city.name}") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF7D5260),
                titleContentColor = Color.White
            )
        ) },
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Date & Time: ${weatherReport.dt_txt}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "Temperature: ${weatherReport.main.temp}°F", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Feels Like: ${weatherReport.main.feels_like}°F", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Weather: ${weatherReport.weather[0].description}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Humidity: ${weatherReport.main.humidity}%", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Wind Speed: ${weatherReport.wind.speed} mph", style = MaterialTheme.typography.bodyLarge)
            Text(text = "City: ${city.name}, ${city.country}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}