package com.tiger.weatherforecast.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.WeatherData
import com.tiger.weatherforecast.data.model.WeatherResponse
import com.tiger.weatherforecast.ui.model.ResultUiState


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    uiState: ResultUiState
) {
    when(uiState){
        is ResultUiState.SuccessScreen -> {
            SuccessScreen(
                currentWeatherResponse = uiState.currentWeatherResponse,
                forecastResponse = uiState.forecastResponse
            )
        }

        is ResultUiState.LoadingScreen -> {
            LoadingScreen()
        }

        is ResultUiState.ErrorScreen -> {
            ErrorScreen()
        }
    }
}

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    currentWeatherResponse: WeatherResponse?,
    forecastResponse: ForecastResponse?
) {
    currentWeatherResponse?.let {
        CurrentWeatherCard(currentWeatherResponse = currentWeatherResponse)
    }
    forecastResponse?.let { 
        ForecastList(forecastResponse = it)
    }
}

@Composable
fun CurrentWeatherCard(
    currentWeatherResponse: WeatherResponse?
){
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        currentWeatherResponse?.name?.let { 
            Text(text = it)
        }
        currentWeatherResponse?.weather?.firstOrNull()?.let { 
            it.main?.let { it1 -> Text(text = it1) }
            it.description?.let { it1 -> Text(text = it1) }
        }
        
    }
}

@Composable
fun ForecastList(
    forecastResponse: ForecastResponse?
){
    LazyRow {
        forecastResponse?.weatherList?.let { list ->
           items(list) { 
               ForecastCard(weatherData = it)
           }
        }
        
    }
}

@Composable
fun ForecastCard(
    weatherData: WeatherData
){
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        weatherData?.weather?.firstOrNull()?.let {
            it.main?.let { it1 -> Text(text = it1) }
            it.description?.let { it1 -> Text(text = it1) }
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Text(text = "Loading...")
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
){
    Text(text = "Something went wrong!")
}