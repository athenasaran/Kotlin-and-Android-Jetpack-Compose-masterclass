package com.example.tictatoe

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictatoe.data.Winner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicTacToeViewModel : ViewModel() {
    private val _moves = MutableStateFlow(List<Boolean?>(9) { null })
    val moves: StateFlow<List<Boolean?>> = _moves

    private val _playerTurn = MutableStateFlow(true)
    val playerTurn: StateFlow<Boolean> = _playerTurn

    private val _winner = MutableStateFlow<Winner?>(null)
    val winner: StateFlow<Winner?> = _winner

    fun onTap(offset: Offset) {
        val movesNew = _moves.value.toMutableList()

        if (_playerTurn.value && _winner.value == null) {
            val x = (offset.x / 333).toInt()
            val y = (offset.y / 333).toInt()
            val posInMoves = y * 3 + x
            if (movesNew[posInMoves] == null) {
                movesNew[posInMoves] = true
                _playerTurn.value = false
                _moves.value = movesNew
                _winner.value = checkEndGame()
            }
        }
    }

    fun computerMove() {
        val movesNew = _moves.value.toMutableList()

        viewModelScope.launch(Dispatchers.IO) {
            delay(1500L)
            while (true) {
                val i = (0 until 9).random()
                if (_moves.value[i] == null) {
                    movesNew[i] = false
                    _playerTurn.value = true
                    _moves.value = movesNew
                    _winner.value = checkEndGame()

                    break
                }
            }
        }
    }

    fun resetGame() {
        _moves.value = List<Boolean?>(9) { null }
        _playerTurn.value = true
        _winner.value = null
    }

    private fun checkEndGame(): Winner? {
        val lines = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        lines.forEach { (a, b, c) ->
            if (_moves.value[a] == true && _moves.value[b] == true && _moves.value[c] == true) {
                return Winner.PLAYER
            } else if (_moves.value[a] == false && _moves.value[b] == false && _moves.value[c] == false) {
                return Winner.COMPUTER
            }
        }

        return if (_moves.value.all { it != null }) Winner.DRAW else null
    }
}