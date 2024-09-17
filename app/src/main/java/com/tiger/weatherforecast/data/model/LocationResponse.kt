package com.tiger.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") val name: String? = null,
    @SerializedName("lat") val latitude: Double? = null,
    @SerializedName("lon") val longitude: Double? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("state") val state: String? = null
)