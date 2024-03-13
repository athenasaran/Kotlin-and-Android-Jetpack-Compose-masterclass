package com.example.visitedcities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun AddCityDialog(onDismiss: () -> Unit, onConfirm: (name: String, country: String) -> Unit) {
    val cityName = remember {
        mutableStateOf(TextFieldValue(String()))
    }
    val country = remember {
        mutableStateOf(TextFieldValue(String()))
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add a city",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = cityName.value,
                    onValueChange = { cityName.value = it },
                    label = { Text(text = "City name") },
                    placeholder = { Text(text = "Rome") },
                    modifier = Modifier.padding(5.dp)
                )
                TextField(
                    value = country.value,
                    onValueChange = { country.value = it },
                    label = { Text(text = "Country") },
                    placeholder = { Text(text = "Italy") },
                    modifier = Modifier.padding(5.dp)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val name = cityName.value.text
                val countryName = country.value.text
                if (name.isNotEmpty() && countryName.isNotEmpty()) {
                    onConfirm(name, countryName)
                }
            }) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}