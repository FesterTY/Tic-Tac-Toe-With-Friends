package com.example.tic_tac_toe

import android.util.Log
import android.widget.ImageButton
import android.widget.Toast

class GameManager {

    var currentPlayer = 0

    fun switchTurn() {
        currentPlayer = 1 - currentPlayer
    }

    fun checkGameOver(buttons: MutableList<ImageButton>): Boolean {
        if (checkBoardFull(buttons))
            return true

        return false
    }

    // Check whether or not board is full
    private fun checkBoardFull(buttons: MutableList<ImageButton>): Boolean {
        var pressedButtons = 0
        for (button in buttons) {
            if (button.tag.toString() == "pressed") {
                pressedButtons++
            }
        }
        if (pressedButtons >= 9) {
            Toast.makeText(buttons[0].context, "Board is full! Cannot continue further.", Toast.LENGTH_LONG).show()
            return true
        }

        return false
    }
}