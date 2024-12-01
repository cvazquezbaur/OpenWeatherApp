package com.mlb.weather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mlb.weather.models.WeatherReport
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Weather(navController: NavHostController, viewModel: WeatherPageViewModel = viewModel()) {
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
            if (viewModel.getZipCode() == null) { zipCodeTextField(viewModel) }
            else {
//                WeatherList(viewModel.getWeatherData())
                Text("Weather Report will load here")
            }
        }
    }
}

@Composable
private fun zipCodeTextField(viewModel: WeatherPageViewModel) {
    TextField(
        value = "Zip Code Here",
        onValueChange = { viewModel.setZipCode(it) },
        label = { Text("Enter Zip Code") },
        modifier = Modifier.padding(16.dp)
    )
    TextField(
        value = "How many days do you want a forecast for?",
        onValueChange = { viewModel.setDayCount(it.toIntOrNull() ?: 5) },
        label = { Text("5 day default") },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun WeatherList(weatherReport: List<WeatherReport>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        for (report in weatherReport) {
            item {
                WeatherCard(report)
            }
        }
    }
}

@Composable
private fun WeatherCard(WeatherReport: WeatherReport) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {

    }
}