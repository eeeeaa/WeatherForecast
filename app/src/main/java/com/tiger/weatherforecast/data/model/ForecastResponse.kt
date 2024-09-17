package com.tiger.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("cod") val code: String? = null,
    @SerializedName("message") val message: Int? = null,
    @SerializedName("cnt") val count: Int? = null,
    @SerializedName("list") val weatherList: List<WeatherData>? = null,
    @SerializedName("city") val city: City? = null
)
