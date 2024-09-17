package com.tiger.weatherforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    query:String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit
){
    Column(modifier = modifier.fillMaxSize()) {
        TextField(value = query, onValueChange = onValueChange)
        Button(onClick = onSearchClick) {
            Text(text = "Search")
        }
    }
}