package com.tiger.weatherforecast.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tiger.weatherforecast.ui.model.ContentType
import com.tiger.weatherforecast.ui.model.ResultUiState
import com.tiger.weatherforecast.ui.model.WeatherUiModel
import com.tiger.weatherforecast.ui.theme.WeatherForecastTheme


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    uiState: ResultUiState,
    contentType: ContentType,
    onBackPressed: () -> Unit
) {

    BackHandler {
        onBackPressed.invoke()
    }

    when (uiState) {
        is ResultUiState.SuccessScreen -> {
            SuccessScreen(
                modifier = modifier,
                currentWeather = uiState.currentWeather,
                forecasts = uiState.forecasts,
                contentType = contentType
            )
        }

        is ResultUiState.LoadingScreen -> {
            LoadingScreen(modifier = modifier)
        }

        is ResultUiState.ErrorScreen -> {
            ErrorScreen(modifier = modifier)
        }
    }
}


@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    contentType: ContentType,
    currentWeather: WeatherUiModel?,
    forecasts: List<WeatherUiModel>
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentWeather?.let {
            CurrentWeatherCard(currentWeather = currentWeather, contentType = contentType)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ForecastWeatherRow(forecastData = forecasts)
    }
}

@Composable
fun CurrentWeatherCard(
    contentType: ContentType,
    currentWeather: WeatherUiModel?,
    modifier: Modifier =Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xCCFFFFFF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            currentWeather?.dayOfWeek?.let {
                Text(text = it, fontSize = 18.sp)
            }
            currentWeather?.time?.let {
                Text(text = it, fontSize = 14.sp, textAlign = TextAlign.Center)
            }
            AsyncImage(
                model = currentWeather?.iconUrl,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            currentWeather?.temperature?.let {
                Text(text = it, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            currentWeather?.weatherDescription?.let {
                Text(text = it, fontSize = 18.sp, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ForecastWeatherRow(
    forecastData: List<WeatherUiModel>,
    modifier: Modifier =Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(forecastData.size) { index ->
            val forecast = forecastData[index]
            ForecastWeatherCard(forecast)
        }
    }
}

@Composable
fun ForecastWeatherCard(
    forecast: WeatherUiModel,
    modifier: Modifier =Modifier
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(160.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xCCFFFFFF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            forecast.dayOfWeek?.let {
                Text(text = it, fontSize = 16.sp)
            }
            forecast.time?.let {
                Text(text = it, fontSize = 12.sp, textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = forecast.iconUrl,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            forecast.temperature?.let {
                Text(text = it, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    WeatherForecastTheme {
        ResultScreen(
            uiState = ResultUiState.SuccessScreen(
                currentWeather = WeatherUiModel(
                    temperature = "25°C",
                    weatherDescription = "Sunny",
                    iconUrl = "test url",
                    time = "17:01",
                    dayOfWeek = "Monday"
                ),
                forecasts = listOf(
                    WeatherUiModel(
                        temperature = "25°C",
                        weatherDescription = "Sunny",
                        iconUrl = "test url",
                        time = "17:01",
                        dayOfWeek = "Monday"
                    ),
                    WeatherUiModel(
                        temperature = "25°C",
                        weatherDescription = "Sunny",
                        iconUrl = "test url",
                        time = "17:01",
                        dayOfWeek = "Monday"
                    ),
                    WeatherUiModel(
                        temperature = "25°C",
                        weatherDescription = "Sunny",
                        iconUrl = "test url",
                        time = "17:01",
                        dayOfWeek = "Monday"
                    )
                )
            ), contentType = ContentType.VERTICAL_CONTENT, onBackPressed = {}
        )
    }
}