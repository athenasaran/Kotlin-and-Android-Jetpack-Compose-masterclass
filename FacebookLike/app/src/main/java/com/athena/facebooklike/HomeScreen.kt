package com.athena.facebooklike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.athena.facebooklike.data.Dessert
import com.athena.facebooklike.data.Fruit
import com.athena.facebooklike.navigation.Destination

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    val desserts = remember { mutableStateOf(Dessert.getAllDesserts()) }
    val fruits = remember { mutableStateOf(Fruit.getAllFruits()) }
    val pageSize = 5

    LazyColumn(modifier = modifier) {
        itemsIndexed(desserts.value.chunked(pageSize)) { _, dessertsChunk ->
            dessertsChunk.forEach { dessert ->
                DessertItem(
                    dessert
                ) {
                    navController.navigate(Destination.Detail.createRoute(dessert.id))
                }
            }
        }

        item {
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(250.dp)
            ) {
                itemsIndexed(fruits.value.chunked(pageSize).flatten()) { _, fruit ->
                    FruitItem(fruit) {
                        navController.navigate(Destination.Detail.createRoute(fruit.id))
                    }
                }
            }
        }
    }
}

@Composable
fun DessertItem(dessert: Dessert, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
        .background(Color.White)
        .clickable {
            onClick()
        }
    ) {
        Text(text = dessert.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
        Text(text = dessert.desc, fontSize = 12.sp, modifier = Modifier.padding(4.dp))
        Image(
            painter = painterResource(dessert.resId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun FruitItem(fruit: Fruit, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .width(150.dp)
        .padding(4.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable {
            onClick()
        }
    ) {
        Image(
            painter = painterResource(fruit.resId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = fruit.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xaaffffff))
                .padding(4.dp)
        )
    }
}