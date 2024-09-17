package com.tiger.weatherforecast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiger.weatherforecast.ui.components.ResultScreen
import com.tiger.weatherforecast.ui.components.SearchScreen
import com.tiger.weatherforecast.ui.model.ContentType
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
    val query = viewModel.query.collectAsStateWithLifecycle().value
    val contentType: ContentType

    when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> {
            contentType = ContentType.VERTICAL_CONTENT
        }

        WindowWidthSizeClass.Expanded -> {
            contentType = ContentType.HORIZONTAL_CONTENT
        }
    }

    if (isResultScreen) {
        ResultScreen(
            uiState = uiState,
            onBackPressed = {
                viewModel.updateIsResultScreen(false)
            }
        )
    } else {
        SearchScreen(
            query = query?:"",
            onValueChange = {
                viewModel.updateQuery(it)
            },
            onSearchClick = {
                viewModel.updateIsResultScreen(true)
                viewModel.queryWeatherData()
            }
        )
    }
}