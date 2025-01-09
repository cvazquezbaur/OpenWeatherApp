package com.my.weather.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.my.weather.data.JsonHandler
import com.my.weather.models.City
import com.my.weather.models.WeatherItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Weather(navController: NavHostController, viewModel: WeatherPageViewModel = viewModel()) {
    val weatherReportState by viewModel.weatherReportState.collectAsState()
    val zipCode by viewModel.zipCode.collectAsState()
    Scaffold(
        topBar = { TopAppBar(
            title = { Text("Weather Report") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF7D5260),
                titleContentColor = Color.White
            )
        ) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (weatherReportState) {
                is WeatherPageViewModel.WeatherReportState.Empty -> {
                    ZipCodeTextField(viewModel)
                }
                is WeatherPageViewModel.WeatherReportState.Loaded -> {
                    val state = weatherReportState as WeatherPageViewModel.WeatherReportState.Loaded
                    Text(
                        text = "5 Day Weather Report for ${state.city.name} in 3 Hour Increments",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
//                    viewModel.getWeatherData()
//                        ?.let { }
                    WeatherList(state.weatherItems, state.city, navController)
                }

                is WeatherPageViewModel.WeatherReportState.Error -> {
                    val state = weatherReportState as WeatherPageViewModel.WeatherReportState.Error
                    Text(
                        text = "Error: ${state.message}",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ZipCodeTextField(viewModel: WeatherPageViewModel) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text("Enter Zip Code") },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
    Button(
        onClick = {
            viewModel.checkCache(text)
        },
        content = { Text("Submit") },
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
private fun WeatherList(weatherReport: List<WeatherItem>, city: City,navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        for (report in weatherReport) {
            item {
                WeatherCard(report, city, navController)
            }
        }
    }
}

@Composable
private fun WeatherCard(weatherReport: WeatherItem, city: City, navController: NavHostController) {
    val jsonHandler = JsonHandler()
    val weatherReportJson = jsonHandler.encodeToJson(weatherReport)
    val cityJson = jsonHandler.encodeToJson(city)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .border(1.dp, Color.Black)
            .clickable {
                Log.d("WeatherCard", "Clicked on ${weatherReport.dt_txt}")
                navController.navigate("weather_info/${weatherReportJson}/${cityJson}")
            }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Weather Report at ${weatherReport.dt_txt}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = weatherReport.main.temp.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Feels Like ${weatherReport.main.feels_like}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = weatherReport.weather[0].description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}