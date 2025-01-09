package com.my.weather.models

/**
 data model for api dump
 ToDo - Refine using Moshi (supposed to be lighter weight) instead of Gson and only have relevant data fields
**/

data class WeatherReport(
    val cnt: Int,
    val list: List<WeatherItem>,
    val city: City
)

data class WeatherItem(
    val dt: Long,
    val main: Main,
    val weather: List<WeatherDetails>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int?,
    val grnd_level: Int?,
    val humidity: Int,
    val temp_kf: Double?
)

data class WeatherDetails(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Clouds(
    val all: Int
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double?
)

data class Sys(
    val pod: String
)

data class City(
    val id: Int,
    val name: String,
    val coord: Coordinates,
    val country: String,
    val population: Int?,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class Coordinates(
    val lon: Double
)
