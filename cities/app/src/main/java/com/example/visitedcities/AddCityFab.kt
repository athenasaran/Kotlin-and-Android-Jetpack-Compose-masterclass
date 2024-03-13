package com.example.visitedcities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddCityFab(onFabClick: () -> Unit) {
    FloatingActionButton(onClick = onFabClick) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}