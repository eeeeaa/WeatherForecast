package com.tiger.weatherforecast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiger.weatherforecast.ui.components.ResultScreen
import com.tiger.weatherforecast.ui.components.SearchScreen
import com.tiger.weatherforecast.ui.model.ResultUiState
import com.tiger.weatherforecast.ui.theme.WeatherForecastTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastTheme {
                val windowSizeClass = calculateWindowSizeClass(activity = this)
                WeatherForecastRoute(windowWidthSizeClass = windowSizeClass.widthSizeClass)
            }
        }
    }
}

@Composable
fun WeatherForecastRoute(
    windowWidthSizeClass: WindowWidthSizeClass,
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState: ResultUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val isResultScreen = viewModel.isResultScreen.collectAsStateWithLifecycle().value
    
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact -> {

        }
        WindowWidthSizeClass.Medium -> {

        }
        WindowWidthSizeClass.Expanded -> {

        }
    }

    if(isResultScreen){
        ResultScreen(uiState = uiState)
    } else {
        SearchScreen(
            onSearchClick = {
                viewModel.updateQuery(it)
                viewModel.updateIsResultScreen(true)
                viewModel.queryWeatherData()
            }
        )
    }
}