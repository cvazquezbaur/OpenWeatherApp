package com.mlb.weather.presentation

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.Modifier
import com.mlb.weather.ui.theme.MLB_WeatherAppTheme

class WeatherLandingFragment : Fragment() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MLB_WeatherAppTheme {
                    Scaffold(
                        topBar = { TopAppBar(
                            title = { Text("Weather Report") }
                        ) },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        WeatherList(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }

    @Composable
    private fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
           Text(
               text = "Weather Report",
               color = MaterialTheme.colorScheme.onPrimary
           )
        }
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

}