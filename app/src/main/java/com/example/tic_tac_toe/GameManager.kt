package com.example.tic_tac_toe

class GameManager {

    var currentPlayer = 0

    fun switchTurn() {
        currentPlayer = 1 - currentPlayer
    }

}