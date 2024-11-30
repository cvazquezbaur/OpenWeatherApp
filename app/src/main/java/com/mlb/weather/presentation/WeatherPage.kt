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
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Weather(navController: NavHostController) {
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
//        WeatherList(Modifier.padding(innerPadding))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            zipCodeTextField()
        }
    }
}

@Composable
private fun zipCodeTextField() {
    TextField(
        value = "Zip Code Here",
        onValueChange = {},
        label = { Text("Enter Zip Code") },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun WeatherList(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            WeatherCard()
        }
    }
}

@Composable
private fun WeatherCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalArrangement = TODO(),
        verticalAlignment = TODO(),
        content = TODO()
    )
}