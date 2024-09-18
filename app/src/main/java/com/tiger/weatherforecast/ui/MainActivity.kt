package com.tiger.weatherforecast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiger.weatherforecast.R
import com.tiger.weatherforecast.ui.components.TopBar
import com.tiger.weatherforecast.ui.screen.ResultScreen
import com.tiger.weatherforecast.ui.screen.SearchScreen
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

    val contentType: ContentType = when (windowWidthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            ContentType.HORIZONTAL_CONTENT
        }

        else -> {
            ContentType.VERTICAL_CONTENT
        }
    }

    Scaffold(
        topBar = {
            TopBar()
        }
    ) { innerPadding ->
        if (isResultScreen) {
            ResultScreen(
                modifier = Modifier.padding(innerPadding),
                uiState = uiState,
                contentType = contentType,
                onBackPressed = {
                    viewModel.updateIsResultScreen(false)
                }
            )
        } else {
            SearchScreen(
                query = query ?: "",
                contentType = contentType,
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
}


