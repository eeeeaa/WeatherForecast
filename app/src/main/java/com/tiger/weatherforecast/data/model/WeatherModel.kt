package com.tiger.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val dt: Long? = null,
    val main: Main? = null,
    val weather: List<Weather>? = null,
    val clouds: Clouds? = null,
    val wind: Wind? = null,
    val visibility: Int? = null,
    val pop: Double? = null,
    val rain: Rain? = null,
    val sys: Sys? = null,
    @SerializedName("dt_txt")
    val dtTxt: String? = null
)

data class Main(
    val temp: Double? = null,
    @SerializedName("feels_like")
    val feelsLike: Double? = null,
    @SerializedName("temp_min")
    val tempMin: Double? = null,
    @SerializedName("temp_max")
    val tempMax: Double? = null,
    val pressure: Int? = null,
    @SerializedName("sea_level")
    val seaLevel: Int? = null,
    @SerializedName("grnd_level")
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    @SerializedName("temp_kf")
    val tempKf: Double? = null
)

data class Weather(
    val id: Int? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)

data class Clouds(
    val all: Int? = null
)

data class Wind(
    val speed: Double? = null,
    val deg: Int? = null,
    val gust: Double? = null
)

data class Rain(
    @SerializedName("1h") val oneHour: Double? = null,
    @SerializedName("3h") val threeHour: Double? = null
)

data class Sys(
    val pod: String? = null
)

data class City(
    val id: Int? = null,
    val name: String? = null,
    val coord: Coord? = null,
    val country: String? = null,
    val population: Int? = null,
    val timezone: Int? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null
)

data class Coord(
    val lat: Double? = null,
    val lon: Double? = null
)