package com.athena.photoedit

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.athena.photoedit.data.TOOL
import com.athena.photoedit.data.ToolButton
import com.athena.photoedit.ui.theme.PhotoEditTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class EditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = defineUri(intent)
        setContent {
            PhotoEditTheme {
                EditScreen(uri)
            }
        }
    }
}

@Composable
fun EditScreen(uri: Uri?) {
    val currentTool = rememberSaveable { mutableStateOf<TOOL?>(null) }
    var brightness by rememberSaveable { mutableFloatStateOf(0f) }
    var saturation by rememberSaveable { mutableFloatStateOf(1f) }

    val tools = listOf(
        ToolButton(TOOL.BRIGHTNESS, R.drawable.ic_bright),
        ToolButton(TOOL.SATURATION, R.drawable.ic_saturation)
    )
    Column(modifier = Modifier.fillMaxSize()) {
        UtilHeader(tools) { currentTool.value = it }
        uri?.let {
            EditImage(uri, brightness, saturation)
            when (currentTool.value) {
                TOOL.BRIGHTNESS -> ToolSlider(brightness, "Brightness", -255f..255f) { brightness = it }
                TOOL.SATURATION -> ToolSlider(saturation, "Saturation", 0f..5f) { saturation = it }
                null -> {}
            }
        }
    }
}

private fun getUriParcelable(intent: Intent): Uri? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
    else intent.getParcelableExtra(Intent.EXTRA_STREAM)
}

private fun defineUri(intent: Intent): Uri? {
    val uriString = intent.getStringExtra("imageUri")
    return uriString?.let { Uri.parse(it) } ?: getUriParcelable(intent)
}

@Composable
fun EditImage(uri: Uri, brightness: Float, saturation: Float) {
    val painter = rememberAsyncImagePainter(uri)
    val matrixFilter = ColorMatrix().apply {
        setToSaturation(saturation)
        this[0, 4] = brightness
        this[1, 4] = brightness
        this[2, 4] = brightness
    }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painter, contentDescription = null, colorFilter = ColorFilter.colorMatrix(matrixFilter))
    }
}

@Composable
fun UtilHeader(tools: List<ToolButton>, onClick: (TOOL) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        items(tools) { tool ->
            ElevatedButton(onClick = { onClick(tool.name) }) {
                Icon(painter = painterResource(id = tool.icon), contentDescription = null)
                Text(tool.name.name, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun ToolSlider(valueFloat: Float, text: String, range: ClosedFloatingPointRange<Float>, onValueChange: (Float) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text)
        Slider(valueRange = range, value = valueFloat, onValueChange = onValueChange)
    }
}