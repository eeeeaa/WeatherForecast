package com.tiger.weatherforecast.ui.utils

import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.WeatherResponse
import com.tiger.weatherforecast.ui.model.WeatherUiModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class UiModelMapper {

    fun mapWeatherResponse(weatherResponse: WeatherResponse?): WeatherUiModel? {
        if (weatherResponse == null || weatherResponse.weather?.firstOrNull() == null) return null

        return WeatherUiModel(
            temperature = "${weatherResponse.main?.temp}°C",
            weatherDescription = weatherResponse.weather.firstOrNull()?.description,
            iconUrl = "https://openweathermap.org/img/wn/${weatherResponse.weather.firstOrNull()?.icon}@2x.png",
            dayOfWeek = getDateString(weatherResponse.dt, "EEEE"),
            time = getDateString(weatherResponse.dt, "HH:mm")
        )
    }

    fun mapForecastResponse(forecastResponse: ForecastResponse?): List<WeatherUiModel> {
        if (forecastResponse == null || forecastResponse.weatherList.isNullOrEmpty()) return emptyList()

        return forecastResponse.weatherList.map {
            WeatherUiModel(
                temperature = "${it.main?.temp}°C",
                weatherDescription = it.weather?.firstOrNull()?.description,
                iconUrl = "https://openweathermap.org/img/wn/${it.weather?.firstOrNull()?.icon}@2x.png",
                time = getDateString(it.dt, "HH:mm"),
                dayOfWeek = getDateString(it.dt, "EEEE")
            )
        }
    }

    private fun getDateString(datetime:Long?, field:String): String? {
        val input = SimpleDateFormat(field, Locale.ENGLISH)

        try {
            return input.format(datetime?.times(1000L) ?: 0)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}