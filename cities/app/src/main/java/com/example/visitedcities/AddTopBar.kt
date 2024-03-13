package com.example.visitedcities

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopBar(onSort: () -> Unit) {
    TopAppBar(
        title = { Text("Visited Cities") },
        actions = {
            IconButton(onClick = onSort) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xff6200ee),
            titleContentColor = Color.White
        )
    )
}