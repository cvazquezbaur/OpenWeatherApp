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
import androidx.navigation.compose.rememberNavController
import com.mlb.weather.presentation.WeatherLandingFragment
import com.mlb.weather.ui.theme.MLB_WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLaunch()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLaunch() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Report") } )
         },
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Weather Report",
            modifier = Modifier.padding(it)
        )
//        NavigationGraph(navController = navController, modifier = Modifier.padding(it))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MLB_WeatherAppTheme {
//        Greeting("Android")
    }
}