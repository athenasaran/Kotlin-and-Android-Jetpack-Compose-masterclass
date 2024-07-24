package com.athena.facebooklike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.athena.facebooklike.data.Dessert
import com.athena.facebooklike.data.Fruit
import com.athena.facebooklike.data.ListItem
import com.athena.facebooklike.data.Person
import com.athena.facebooklike.data.Shortcut

@Composable
fun NavigationDrawer(randomItems: List<ListItem>, shortcuts: List<Shortcut>) {
    Column(modifier = Modifier.background(Color(0xFFEEEEEE))) {
        UserProfileHeader()
        ShortcutsHeader()
        DisplayItems(randomItems)
        AllShortcutsTitle()
        DisplayShortcuts(shortcuts)
    }
}

@Composable
fun UserProfileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )
        Text(text = "User Name", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ShortcutsHeader() {
    Text(text = "Shortcuts", modifier = Modifier.padding(8.dp))
}

@Composable
fun DisplayItems(randomItems: List<ListItem>) {
    LazyRow {
        itemsIndexed(
            items = randomItems
        ) { _, item ->
            val (resId, title, shape) = when (item) {
                is Dessert -> Triple(item.resId, item.title, RoundedCornerShape(8.dp))
                is Fruit -> Triple(item.resId, item.name, RoundedCornerShape(8.dp))
                is Person -> Triple(item.resId, item.name, CircleShape)
                else -> Triple(0, "", CircleShape)
            }

            ItemDisplay(resId, title, shape)
        }
    }
}

@Composable
fun ItemDisplay(resId: Int, title: String?, shape: Shape) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(shape),
            contentScale = ContentScale.Crop
        )
        title?.let {
            Text(text = it, fontSize = 12.sp)
        }
    }
}

@Composable
fun AllShortcutsTitle() {
    Text(text = "All Shortcuts", modifier = Modifier.padding(8.dp))
}

@Composable
fun DisplayShortcuts(shortcuts: List<Shortcut>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
        itemsIndexed(shortcuts) { _, shortcut ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = shortcut.resId),
                    contentDescription = null,
                    tint = shortcut.tint
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = shortcut.title, fontSize = 12.sp)
            }
        }
    }
}