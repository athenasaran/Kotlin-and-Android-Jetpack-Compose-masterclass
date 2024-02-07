package com.example.tictatoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictatoe.ui.theme.TicTaToeTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TicTacToeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeApp {
                TicTaToeTheme {
                    TTTScreen(Modifier, viewModel)
                }
            }
        }
    }
}

@Composable
fun TicTacToeApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    TicTacToeApp {
        TTTScreen(Modifier, TicTacToeViewModel())
    }
}