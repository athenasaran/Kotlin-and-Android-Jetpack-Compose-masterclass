package com.athena.facebooklike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.athena.facebooklike.data.Person

@Composable
fun NotificationsScreen(modifier: Modifier) {
    val people = remember { mutableStateOf(Person.getPeople()) }

    LazyColumn(modifier = modifier.background(Color.White)) {
        itemsIndexed(people.value) { _, person ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                    .background(Color(0xF6BBE1FF))
            ) {
                Image(
                    painter = painterResource(id = person.resId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(shape = CircleShape)
                        .size(50.dp)
                )
                Text(text = "${person.name} ${person.text}")
            }
        }
    }
}

@Preview
@Composable
private fun NotificationsScreenPreview() {
    NotificationsScreen(modifier = Modifier)
}