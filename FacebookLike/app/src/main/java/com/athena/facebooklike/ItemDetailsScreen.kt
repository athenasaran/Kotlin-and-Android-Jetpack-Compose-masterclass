package com.athena.facebooklike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.athena.facebooklike.data.Dessert
import com.athena.facebooklike.data.Fruit
import com.athena.facebooklike.data.getItem

@Composable
fun ItemDetailsScreen(itemId: Int, modifier: Modifier) {
    val itemDetails = when (val item = getItem(itemId)) {
        is Dessert -> ItemDetails(item.resId, item.title, item.desc, item.title)
        is Fruit -> ItemDetails(item.resId, item.name, item.desc, item.origin)
        else -> null
    }

    itemDetails?.let {
        Column(modifier = modifier.background(color = Color.White)) {
            Image(
                painter = painterResource(id = it.resId),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = it.title,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = it.origin,
                fontSize = 12.sp,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = it.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

data class ItemDetails(val resId: Int, val title: String, val description: String, val origin: String)