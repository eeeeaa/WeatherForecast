package com.tiger.weatherforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit
){
    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = {
            onSearchClick.invoke("bangkok")
        }) {
            Text(text = "Search")
        }
    }
}