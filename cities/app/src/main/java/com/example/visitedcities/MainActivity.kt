package com.example.visitedcities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.visitedcities.model.City
import com.example.visitedcities.ui.theme.VisitedCitiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisitedCitiesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val cities = remember { mutableListOf<City>() }
                    val sort = remember { mutableStateOf(false) }
                    val sortedCities = if (sort.value) cities.sortedBy { it.name } else cities

                    CitiesList(
                        list = sortedCities,
                        onAddCiy = { name, country ->
                            cities.add(City(name, country))
                        },
                        onSort = {
                            sort.value = !sort.value
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VisitedCitiesTheme {
        CitiesList(
            listOf(City("Rome", "Italy"), City("Paris", "France")),
            onAddCiy = { _, _ -> },
            onSort = { }
        )
    }
}