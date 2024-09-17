package com.tiger.weatherforecast.repository

import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.Location
import com.tiger.weatherforecast.data.model.WeatherResponse
import com.tiger.weatherforecast.repository.model.ResultWrapper

interface WeatherRepository {
    suspend fun fetchCurrentWeatherForecast(
        latitude: Double?,
        longitude: Double?
    ): ResultWrapper<WeatherResponse?>

    suspend fun fetchFiveDayWeatherForecast(
        latitude: Double?,
        longitude: Double?
    ): ResultWrapper<ForecastResponse?>

    suspend fun queryGeographicLocationByName(name: String): ResultWrapper<List<Location>?>
}