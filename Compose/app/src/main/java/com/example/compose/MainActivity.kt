package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    InstagramLayout(
                        painterResource(id = R.drawable.person),
                        120,
                        12000,
                        22,
                        "Athena",
                        "Desenvolvedora Android"
                    )
                }
            }
        }
    }
}

@Composable
fun InstagramLayout(
    picture: Painter,
    post: Int,
    followers: Int,
    following: Int,
    name: String,
    description: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(150.dp)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier.clip(CircleShape)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = post.toString(), fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.post), fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = followers.toString(), fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.followers), fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = following.toString(), fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.following), fontWeight = FontWeight.Bold)
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(1f)

        ) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = description)
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.edit_profile),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Column {
            Row(modifier = Modifier.fillMaxWidth(1f)) {
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 1.dp, bottom = 1.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 1.dp, bottom = 1.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 1.dp, bottom = 1.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Row(modifier = Modifier.fillMaxWidth(1f)) {
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 1.dp, bottom = 1.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = picture,
                    contentDescription = String(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 1.dp, bottom = 1.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    InstagramLayout(
        picture = painterResource(id = R.drawable.person),
        post = 120,
        followers = 12000,
        following = 22,
        name = "Athena",
        description = "Desenvolvedora Android"
    )
}