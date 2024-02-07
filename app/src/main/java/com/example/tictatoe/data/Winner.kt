package com.example.tictatoe.data

sealed class Winner {
    data object PLAYER : Winner()
    data object COMPUTER : Winner()
    data object DRAW : Winner()
}