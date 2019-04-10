package com.example.tic_tac_toe

import android.app.Activity
import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import java.lang.Math.sqrt

class GameManager(_context: Context, _activity: Activity) {

    private val context = _context
    private val activity = _activity
    var currentPlayer = 0

    fun switchTurn() {
        currentPlayer = 1 - currentPlayer
    }

    fun checkGameOver(buttons: MutableList<ImageButton>): Boolean {
        if (checkVertical(buttons))
            return true
        else if (checkBoardFull(buttons))
            return true

        return false
    }

    private fun checkVertical(buttons: MutableList<ImageButton>): Boolean {

        val gridSize = Math.floor(sqrt(buttons.size.toDouble())).toInt()
        for (col in 1..gridSize) {
            var currentBoxNum = col
            val buttonsInCol = arrayListOf<String>()

            for (rows in 1..gridSize) {
                buttonsInCol += activity.findViewById<ImageButton>(context.resources.getIdentifier("ttt_box$currentBoxNum", "id", context.packageName)).tag.toString()
                currentBoxNum += gridSize
            }

            // Once it's done adding all buttons to the arrayList
            if ((buttonsInCol.count { it == buttonsInCol[0] } == buttonsInCol.size) && buttonsInCol[0] != PlayerInfo.defaultTag) {
                Toast.makeText(context, "${buttonsInCol[0]} is the winner!", Toast.LENGTH_LONG).show()
                return true
            }
        }

        return false
    }

    // Check whether or not board is full
    private fun checkBoardFull(buttons: MutableList<ImageButton>): Boolean {
        var pressedButtons = 0
        for (button in buttons) {
            if (button.tag.toString() == "x" || button.tag.toString() == "o") {
                pressedButtons++
            }
        }
        if (pressedButtons >= 9) {
            Toast.makeText(context, "Board is full! Cannot continue further.", Toast.LENGTH_LONG).show()
            return true
        }

        return false
    }
}