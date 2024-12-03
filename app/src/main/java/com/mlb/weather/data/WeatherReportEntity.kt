package com.mlb.weather.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mlb.weather.models.City
import com.mlb.weather.models.WeatherItem

@Entity(tableName = "weather_report_table")
@TypeConverters(WeatherTypeConverters::class) // Use custom converters
data class WeatherReportEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Unique key for the city
    val cityName: String,
    val cnt: Int,
    val list: List<WeatherItem>,
    val city: City
)