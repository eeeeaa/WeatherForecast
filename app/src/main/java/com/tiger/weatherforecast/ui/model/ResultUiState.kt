package com.tiger.weatherforecast.ui.model

import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.WeatherResponse

sealed class ResultUiState {
    data class SuccessScreen(val currentWeather: WeatherUiModel?, val forecasts: List<WeatherUiModel>): ResultUiState()
    data object LoadingScreen:ResultUiState()
    data object ErrorScreen: ResultUiState()
}