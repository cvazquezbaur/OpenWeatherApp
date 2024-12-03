package com.mlb.weather.data

class WeatherRepo(private val weatherDao: WeatherDao) {

    suspend fun insertWeather(weather: WeatherReportEntity) {
        weatherDao.insertWeatherReport(weather)
    }

    //ToDo future updates would include using
    suspend fun getWeather(id: Int): WeatherReportEntity? { // Generate the ID used for caching
        return weatherDao.getWeatherReport(id)
    }

    suspend fun deleteOldWeather(thresholdDate: String) {
        weatherDao.deleteWeatherReport(thresholdDate.toInt())
    }
}