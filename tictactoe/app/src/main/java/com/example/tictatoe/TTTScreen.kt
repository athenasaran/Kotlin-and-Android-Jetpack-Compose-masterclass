package com.example.tictatoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictatoe.data.Winner

@Composable
fun TTTScreen(modifier: Modifier = Modifier, viewModel: TicTacToeViewModel = viewModel()) {
    val moves by viewModel.moves.collectAsState()
    val playerTurn by viewModel.playerTurn.collectAsState()
    val winner by viewModel.winner.collectAsState()

    val startColor = Color(0xFF00D2FF)
    val endColor = Color(0xFF3A7BD5)
    val gradient = Brush.linearGradient(listOf(startColor, endColor))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(gradient)
    ) {

        Header(playerTurn = playerTurn)
        Board(moves = moves, onTap = { offset -> viewModel.onTap(offset) })

        //Computer's move
        if (!playerTurn && winner == null) {
            CircularProgressIndicator(color = Color(0xFF3A7BD5), modifier = Modifier.padding(16.dp))
            LaunchedEffect(key1 = Unit) {
                viewModel.computerMove()
            }
        }

        if (winner != null) {
            when (winner) {
                Winner.PLAYER -> {
                    Text(
                        text = stringResource(R.string.player_wins),
                        fontSize = 30.sp,
                        color = Color(0xFF2D29DF),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Winner.COMPUTER -> {
                    Text(
                        text = stringResource(R.string.computer_wins),
                        fontSize = 30.sp,
                        color = Color(0xFF2D29DF),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Winner.DRAW -> {
                    Text(
                        text = stringResource(R.string.it_s_a_draw),
                        fontSize = 30.sp,
                        color = Color(0xFF2D29DF),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {}
            }

            Button(modifier.padding(vertical = 8.dp)) {
                viewModel.resetGame()
            }
        }
    }

    LaunchedEffect(key1 = winner) {
        if (winner == Winner.COMPUTER) {
            viewModel.computerMove()
        }
    }
}

@Composable
private fun Button(modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6190E8),
                contentColor = Color.White
            ),
            onClick = onClick
        ) {
            Text(
                text = stringResource(R.string.play_again),
                fontSize = 32.sp
            )
        }
    }
}

@Composable
fun Header(playerTurn: Boolean) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val boxColor = if (playerTurn) Color(0xFF7B61FF) else Color(0xFF3A7BD5)
        val textPlayer = if (playerTurn) R.string.player else R.string.computer

        Text(
            text = stringResource(R.string.tic_tac_toe),
            modifier = Modifier.padding(16.dp),
            fontSize = 36.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(textPlayer),
            modifier = Modifier.padding(8.dp),
            color = boxColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Board(moves: List<Boolean?>, onTap: (Offset) -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(32.dp)
            .border(2.dp, Color.Transparent, shape = MaterialTheme.shapes.extraLarge)
            .background(Color.White, shape = MaterialTheme.shapes.extraLarge)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = onTap
                )
            }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(horizontal = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(1f)
                    .background(Color.LightGray)
            ) {}
            Row(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(1f)
                    .background(Color.LightGray)
            ) {}
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                .fillMaxSize(1f)
                .padding(vertical = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight(1f)
                    .background(Color.LightGray)
            ) {}
            Column(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight(1f)
                    .background(Color.LightGray)
            ) {}
        }
        Column(modifier = Modifier.fillMaxSize(1f)) {
            for (i in 0..2) {
                Row(modifier = Modifier.weight(1f)) {
                    for (j in 0..2) {
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                            GetComposableFromMovie(moves[i * 3 + j])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GetComposableFromMovie(move: Boolean?) {
    when (move) {
        true -> {
            Image(
                painter = painterResource(id = R.drawable.x),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(1f)
            )
        }

        false -> {
            Image(
                painter = painterResource(id = R.drawable.o),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(1f)
            )
        }

        else -> {
            Image(
                painter = painterResource(id = R.drawable.ic_null),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TTTScreen()
}