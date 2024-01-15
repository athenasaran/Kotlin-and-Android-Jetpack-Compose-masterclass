package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val counter =
                        remember { mutableStateOf(0) } // maintain this state (remember) and create provided the default value, but is mutable
                    val onClick = { counter.value += 1 }
                    /*
                        val counter = remember { mutableStateOf(0) }
                        val onClick = { counter.value += 1 }

                        var counter by remember { mutableStateOf(0) }
                        val onClick = { counter += 1 }

                        val (counter, setCounter) = remember { mutableStateOf(0) }
                        val onClick = { setCounter(counter + 1) }
                     */
                    ClickCounter(count = counter.value, onClick)
                }
            }
        }
    }
}

@Composable // can run in parallel, run in any order, run any number of times or be skipped for some frames, optimist - might not finish, no time intensive work
fun ClickCounter(
    count: Int,
    onClick: () -> Unit
) { //state hoisting -> keep the composable functions as simple, without maintaining too much state
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onClick) {
            Text(text = "Button clicked $count times")
        }
    }// recomposition -> composable will be redrawn, system recognize when needs this because the composable have a tree, when change only functions are redrawn, recomposition occurs when state changes
}// always verify side effects