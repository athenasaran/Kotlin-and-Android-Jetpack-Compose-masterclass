package com.example.visitedcities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.visitedcities.model.City

@Composable
fun CitiesList(
    list: List<City>,
    onAddCiy: (name: String, country: String) -> Unit,
    onSort: () -> Unit
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = { AddCityFab { showDialog.value = true } },
        topBar = { AddTopBar(onSort) }
    ) {
        if (list.isEmpty())
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "No cities available")
            }
        else
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 64.dp)
            ) {
                items(list) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xffeeeeee))
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = it.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = it.country)
                    }
                }
            }
    }

    if (showDialog.value) {
        AddCityDialog(
            onDismiss = { showDialog.value = false },
            onConfirm = { name, country ->
                showDialog.value = false
                onAddCiy.invoke(name, country)
            }
        )
    }
}