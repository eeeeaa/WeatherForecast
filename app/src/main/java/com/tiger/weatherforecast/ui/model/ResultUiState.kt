package com.tiger.weatherforecast.ui.model

import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.WeatherResponse

sealed class ResultUiState {
    data class SuccessScreen(val currentWeatherResponse: WeatherResponse?,
                             val forecastResponse: ForecastResponse?): ResultUiState()
    data object LoadingScreen:ResultUiState()
    data object ErrorScreen: ResultUiState()
}