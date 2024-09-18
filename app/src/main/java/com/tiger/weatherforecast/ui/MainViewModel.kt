package com.tiger.weatherforecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiger.weatherforecast.repository.WeatherRepository
import com.tiger.weatherforecast.repository.model.ResultWrapper
import com.tiger.weatherforecast.ui.model.ResultUiState
import com.tiger.weatherforecast.ui.utils.UiModelMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository,
    private val uiModelMapper: UiModelMapper
) : ViewModel() {

    private val _query: MutableStateFlow<String?> = MutableStateFlow(null)
    val query: StateFlow<String?> get() = _query.asStateFlow()

    private val _isResultScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isResultScreen: StateFlow<Boolean> get() = _isResultScreen.asStateFlow()

    private val _uiState: MutableStateFlow<ResultUiState> = MutableStateFlow(
        ResultUiState.LoadingScreen
    )
    val uiState: StateFlow<ResultUiState> get() = _uiState.asStateFlow()

    fun queryWeatherData() = viewModelScope.launch {
        _uiState.update {
            ResultUiState.LoadingScreen
        }

        _query.value?.let {
            when (val result = weatherRepository.queryGeographicLocationByName(it)) {
                is ResultWrapper.Success -> {
                    val firstLocation = result.data?.firstOrNull()
                    val currentWeather = weatherRepository.fetchCurrentWeatherForecast(
                        latitude = firstLocation?.latitude,
                        longitude = firstLocation?.longitude
                    )
                    val forecast = weatherRepository.fetchFiveDayWeatherForecast(
                        latitude = firstLocation?.latitude,
                        longitude = firstLocation?.longitude
                    )

                    when {
                        currentWeather is ResultWrapper.Success && forecast is ResultWrapper.Success -> {
                            _uiState.update {
                                ResultUiState.SuccessScreen(
                                    currentWeather = uiModelMapper.mapWeatherResponse(currentWeather.data),
                                    forecasts = uiModelMapper.mapForecastResponse(forecast.data)
                                )
                            }
                        }

                        else -> {
                            _uiState.update {
                                ResultUiState.ErrorScreen
                            }
                        }
                    }
                }

                else -> {
                    _uiState.update {
                        ResultUiState.ErrorScreen
                    }
                }
            }
        }
    }

    fun updateQuery(name: String) {
        _query.update { name }
    }

    fun updateIsResultScreen(value: Boolean) {
        _isResultScreen.update { value }
    }
}