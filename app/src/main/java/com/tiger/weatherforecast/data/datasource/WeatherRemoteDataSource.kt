package com.tiger.weatherforecast.data.datasource

import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.Location
import com.tiger.weatherforecast.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDataSource {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null
    ): Response<WeatherResponse?>

    @GET("/data/2.5/forecast")
    suspend fun getFiveDayForecast(
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null
    ): Response<ForecastResponse?>

    @GET("/geo/1.0/direct")
    suspend fun getGeographicLocationByName(
        @Query("q") name: String? = null,
        @Query("limit") limit: Int = 1
    ): Response<List<Location>?>
}