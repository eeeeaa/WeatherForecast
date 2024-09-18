package com.tiger.weatherforecast.ui.model

data class WeatherUiModel(
    val temperature: String? = null,
    val weatherDescription: String? = null,
    val iconUrl: String? = null,
    val dayOfWeek: String? = null,
    val time:String? = null
)