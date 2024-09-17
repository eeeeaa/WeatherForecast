package com.tiger.weatherforecast.repository

import com.tiger.weatherforecast.data.datasource.WeatherRemoteDataSource
import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.Location
import com.tiger.weatherforecast.data.model.WeatherResponse
import com.tiger.weatherforecast.repository.model.ResultWrapper
import com.tiger.weatherforecast.repository.model.handleApi

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun fetchCurrentWeatherForecast(
        latitude: Double?,
        longitude: Double?
    ): ResultWrapper<WeatherResponse?> = handleApi {
        weatherRemoteDataSource.getCurrentWeather(lat = latitude, lon = longitude)
    }

    override suspend fun fetchFiveDayWeatherForecast(
        latitude: Double?,
        longitude: Double?
    ): ResultWrapper<ForecastResponse?> = handleApi {
        weatherRemoteDataSource.getFiveDayForecast(lat = latitude, lon = longitude)
    }

    override suspend fun queryGeographicLocationByName(name: String): ResultWrapper<List<Location>?> =
        handleApi {
            weatherRemoteDataSource.getGeographicLocationByName(name)
        }
}