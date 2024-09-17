package com.tiger.weatherforecast.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tiger.weatherforecast.R
import com.tiger.weatherforecast.data.model.ForecastResponse
import com.tiger.weatherforecast.data.model.WeatherData
import com.tiger.weatherforecast.data.model.WeatherResponse
import com.tiger.weatherforecast.ui.model.ResultUiState


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    uiState: ResultUiState,
    onBackPressed: () -> Unit
) {

    BackHandler {
        onBackPressed.invoke()
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar()
        }
    ) { innerPadding ->
        when (uiState) {
            is ResultUiState.SuccessScreen -> {
                SuccessScreen(
                    modifier = Modifier.padding(innerPadding),
                    currentWeatherResponse = uiState.currentWeatherResponse,
                    forecastResponse = uiState.forecastResponse
                )
            }

            is ResultUiState.LoadingScreen -> {
                LoadingScreen(modifier = Modifier.padding(innerPadding))
            }

            is ResultUiState.ErrorScreen -> {
                ErrorScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
        Text(text = stringResource(id = R.string.app_name))
    })
}

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    currentWeatherResponse: WeatherResponse?,
    forecastResponse: ForecastResponse?
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentWeatherResponse?.let {
            CurrentWeatherCard(currentWeatherResponse = currentWeatherResponse)
        }
        forecastResponse?.let {
            ForecastList(forecastResponse = it)
        }
    }
}

@Composable
fun CurrentWeatherCard(
    currentWeatherResponse: WeatherResponse?
) {
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
) {
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
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        weatherData.weather?.firstOrNull()?.let {
            it.main?.let { it1 -> Text(text = it1) }
            it.description?.let { it1 -> Text(text = it1) }
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Something went wrong!")
    }
}