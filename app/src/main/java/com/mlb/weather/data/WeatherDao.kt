package com.mlb.weather.data

import androidx.room.*

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherReport(weatherReport: WeatherReportEntity)

    @Query("SELECT * FROM weather_report_table WHERE id = :id")
    suspend fun getWeatherReport(id: Int): WeatherReportEntity?

    @Query("DELETE FROM weather_report_table WHERE id = :id")
    suspend fun deleteWeatherReport(id: Int)
}